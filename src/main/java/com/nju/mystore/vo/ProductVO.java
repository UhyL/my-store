package com.nju.mystore.vo;

import com.nju.mystore.enums.ProductEnum;
import com.nju.mystore.po.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductVO {
    private Integer productId;

    private Integer productStoreId;

    private String productName;

    private String productDescription;

    private Integer productSales;

    private Double productPrice;

    private ProductEnum productCategory;

    private String productImageUrl;

    private Double productScore;

    private Integer commentNum;


    public Product toPO() {
        Product product = new Product();
        product.setProductId(this.productId);
        product.setProductName(this.productName);
        product.setProductPrice(this.productPrice);
        product.setProductStoreId(this.productStoreId);
        product.setProductDescription(this.productDescription);
        product.setProductSales(this.productSales);
        product.setProductCategory(this.productCategory);
        product.setProductImageUrl(this.productImageUrl);
        product.setProductScore(this.productScore);
        product.setCommentNum(this.commentNum);
        return product;
    }
}
