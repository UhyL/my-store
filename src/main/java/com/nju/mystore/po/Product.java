package com.nju.mystore.po;


import com.nju.mystore.enums.ProductEnum;
import com.nju.mystore.vo.ProductVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    /**
     * 商品id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private Integer productId;


    /**
     * 商品所属商店的id
     */
    @Basic
    @Column(name = "product_store_id")
    private Integer productStoreId;

    /**
     * 商品名字
     */
    @Basic
    @Column(name = "product_name")
    private String productName;

    /**
     * 描述商品
     */
    @Basic
    @Column(name = "description")
    private String productDescription;


    /**
     * 表示商品在销售数量
     */
    @Basic
    @Column(name = "sales")
    private Integer productSales;

    /**
     * 表示商品的价格
     */
    @Basic
    @Column(name = "price")
    private Double productPrice;

    /**
     * 表示商品种类
     */
    @Basic
    @Column(name = "product_category")
    @Enumerated(EnumType.STRING)
    private ProductEnum productCategory;

    /**
     * 图片url
     */
    @Basic
    @Column(name = "image_url")
    private String productImageUrl;

    /**
     * 商品的评分
     */
    @Basic
    @Column(name = "product_score")
    private Double productScore;

    /**
     * 商品的评论数量
     */

    @Basic
    @Column(name = "comment_num")
    private Integer commentNum;


    public ProductVO toVO() {
        ProductVO productVO = new ProductVO();
        productVO.setProductId(this.productId);
        productVO.setProductName(this.productName);
        productVO.setProductStoreId(this.productStoreId);
        productVO.setProductPrice(this.productPrice);
        productVO.setProductSales(this.productSales);
        productVO.setProductDescription(this.productDescription);
        productVO.setProductCategory(this.productCategory);
        productVO.setProductImageUrl(this.productImageUrl);
        productVO.setProductScore(this.productScore);
        productVO.setCommentNum(this.commentNum);
        return productVO;
    }
}
