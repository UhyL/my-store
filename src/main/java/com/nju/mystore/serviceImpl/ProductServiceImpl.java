package com.nju.mystore.serviceImpl;


import com.nju.mystore.enums.ImageBelongEnum;
import com.nju.mystore.enums.ProductEnum;
import com.nju.mystore.exception.BlueWhaleException;
import com.nju.mystore.po.Image;
import com.nju.mystore.po.Invoice;
import com.nju.mystore.po.Product;
import com.nju.mystore.po.User;
import com.nju.mystore.repository.ImageRepository;
import com.nju.mystore.repository.InvoiceRepository;
import com.nju.mystore.repository.ProductRepository;
import com.nju.mystore.repository.UserRepository;
import com.nju.mystore.service.ProductService;
import com.nju.mystore.util.OssUtil;
import com.nju.mystore.vo.CommentVO;
import com.nju.mystore.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    OssUtil ossUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    EntityManager entityManager;

    @Override
    public Integer createProduct(ProductVO productVO) {
        List<Product> list = productRepository.findAllByProductStoreId(productVO.getProductStoreId());
        for (Product product : list) {
            if (Objects.equals(product.getProductName(), productVO.getProductName()))
                throw BlueWhaleException.productAlreadyExists();
        }
        Product newProduct = productVO.toPO();
        productRepository.save(newProduct);

        Image image = new Image();
        image.setImageBelong(ImageBelongEnum.PRODUCT);
        image.setBelongId(newProduct.getProductId());
        image.setOssUrl(newProduct.getProductImageUrl());
        imageRepository.save(image);
        return newProduct.getProductId();
    }

    @Override
    public ProductVO getProductById(Integer productId) {
        Product product = productRepository.findByProductId(productId);
        if (product == null) {
            throw BlueWhaleException.productNotFound();
        }
        return product.toVO();
    }

    @Override
    public Boolean updateProductSales(Integer productId, Integer productSales) {
        return productRepository.updateProductBySales(productId, productSales) != 0;
    }

    @Override
    public Boolean addMultiPicture(int productId, String picture) {
        ProductVO productVO = getProductById(productId);

        Image image = new Image();
        image.setImageBelong(ImageBelongEnum.PRODUCT);
        image.setBelongId(productVO.getProductId());
        image.setOssUrl(picture);
        imageRepository.save(image);

        return true;
    }

    @Override
    public List<ProductVO> getAllProducts(Integer productStoreId) {
        List<Product> list = productRepository.findAllByProductStoreId(productStoreId);
        return list.stream().map(Product::toVO).collect(Collectors.toList());
    }

    @Override
    public List<CommentVO> getAllComments(Integer productId) {
        Product product = productRepository.findByProductId(productId);
        if (product == null) {
            throw BlueWhaleException.productNotFound();
        }
        List<CommentVO> comments = new ArrayList<>();
        List<Invoice> invoices = invoiceRepository.findAllByInvoiceProductId(productId);

        for (Invoice invoicesItem : invoices) {
            int userId = invoicesItem.getInvoiceUserId();
            User user = userRepository.findById(userId).get();
            CommentVO commentVO = new CommentVO();
            commentVO.setComment(invoicesItem.getInvoiceRemark());
            commentVO.setScore(invoicesItem.getInvoiceScore());
            commentVO.setTime(invoicesItem.getInvoiceCommentTime());
            commentVO.setUsername(user.getName());
            comments.add(commentVO);
        }
        return comments;
    }

    @Override
    public List<ProductVO> searchProduct(Integer storeId, String name, ProductEnum type, Double minPrice, Double maxPrice) {
        String condition = "SELECT p FROM Product p WHERE 1=1";
        if (storeId != null && storeId != 0) {
            condition = condition.concat(" AND p.productStoreId = :storeId");
        }

        if (name != null && !name.isEmpty()) {
            condition = condition.concat(" AND p.productName LIKE :name");
        }

        if (type != null) {
            condition = condition.concat(" AND p.productCategory = :type");
        }

        if (minPrice != null && minPrice > 0) {
            condition = condition.concat(" AND p.productPrice >= :minPrice");
        }

        if (maxPrice != null && maxPrice > 0) {
            condition = condition.concat(" AND p.productPrice <= :maxPrice");
        }

        Query query = entityManager.createQuery(condition);

        if (storeId != null && storeId != 0) {
            query.setParameter("storeId", storeId);
        }

        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }

        if (type != null) {
            query.setParameter("type", type);
        }

        if (minPrice != null && minPrice > 0) {
            query.setParameter("minPrice", minPrice);
        }

        if (maxPrice != null && maxPrice > 0) {
            query.setParameter("maxPrice", maxPrice);
        }

        List<Product> products = query.getResultList();
        return products.stream().map(Product::toVO).collect(Collectors.toList());
    }

}
