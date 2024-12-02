package com.nju.mystore.service;

import com.nju.mystore.enums.ProductEnum;
import com.nju.mystore.vo.CommentVO;
import com.nju.mystore.vo.ProductVO;

import java.util.List;

public interface ProductService {
    Integer createProduct(ProductVO productVO);

    ProductVO getProductById(Integer productId);

    Boolean updateProductSales(Integer productId, Integer productSales);

    Boolean addMultiPicture(int productId, String picture);

    List<ProductVO> getAllProducts(Integer productStoreId);

    List<CommentVO> getAllComments(Integer productId);

    List<ProductVO> searchProduct(Integer storeId, String name, ProductEnum type, Double minPrice, Double maxPrice);

}
