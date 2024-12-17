package com.nju.mystore.service;

import com.nju.mystore.po.product.NewProduct;
import com.nju.mystore.po.product.ProductAttribute;
import com.nju.mystore.po.product.ProductOption;
import com.nju.mystore.po.product.ProductOptionValue;
import com.nju.mystore.vo.product.CommentVO;

import java.util.List;
import java.util.Map;

public interface NewProductService {

    /**
     * 获取特定类别的商品的属性，如获取服饰的“部位”“季节”
     */
    List<ProductAttribute> getAttributesByCategory(String productCategory);

    /**
     * 根据类别和属性筛选商品
     */
    List<NewProduct> filterProducts(String productCategory, Map<String, String> filters);

    List<CommentVO> getCommentsByProductId(Integer productId);

    Boolean addComment(CommentVO commentVO);

    List<ProductOption> getProductOptions(Integer productId);

    List<ProductOptionValue> getProductOptionValues(Integer productId);
}
