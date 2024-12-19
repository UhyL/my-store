package com.nju.mystore.serviceImpl.product;

import com.nju.mystore.enums.product.ProductCategory;
import com.nju.mystore.po.product.*;
import com.nju.mystore.repository.product.*;
import com.nju.mystore.service.NewProductService;
import com.nju.mystore.vo.product.CommentVO;
import com.nju.mystore.vo.product.NewProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 有这个就可以不用AutoWeired, 自动装配成员变量设置成final
public class NewProductServiceImpl implements NewProductService {

    private final NewProductRepository productRepository;

    private final ProductAttributeRepository productAttributeRepository;

    private final ProductAttributeValueRepository productAttributeValueRepository;

    private final CommentRepository commentRepository;

    private final ProductOptionRepository productOptionRepository;

    private final ProductOptionValueRepository productOptionValueRepository;

    /**
     * 获取特定类别的商品的属性，如获取服饰的“部位”“季节”
     */
    public List<ProductAttribute> getAttributesByCategory(String productCategory) {
        return productAttributeRepository.findByProductCategory(productCategory);
    }

    /**
     * 根据类别和属性筛选商品
     */
    public List<NewProduct> filterProducts(String productCategory, Map<String, String> filters) {
        // 根据类别筛选商品
        ProductCategory category = ProductCategory.valueOf(productCategory);
        List<NewProduct> products = productRepository.findByProductCategory(category);

        // 根据属性动态筛选
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            String attributeName = filter.getKey();
            String value = filter.getValue();
            List<NewProduct> filteredProducts = productAttributeValueRepository
                    .findProductsByAttribute(attributeName, value);
            products.retainAll(filteredProducts); // 取交集
        }

        return products;
    }

    @Override
    public List<CommentVO> getCommentsByProductId(Integer productId) {
        List<Comment> comments = commentRepository.findByProductId(productId);
        return comments.stream().map(Comment::toVO).collect(Collectors.toList());
    }

    @Override
    public Boolean addComment(CommentVO commentVO) {
        commentRepository.save(commentVO.toPO());
        return true;
    }

    @Override
    public List<ProductOption> getProductOptions(Integer productId) {
        return productOptionRepository.findByProductId(productId);
    }

    @Override
    public List<ProductOptionValue> getProductOptionValues(Integer productId) {
        return productOptionValueRepository.findByProductId(productId);
    }

    @Override
    public List<NewProductVO> getAllDiscountProducts() {
        List<NewProduct> products = productRepository.findProductsWithMismatchedPrices();
        return products.stream().map(NewProduct::toVO).collect(Collectors.toList());
    }
}
