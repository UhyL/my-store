package com.nju.mystore.po.product;

import com.nju.mystore.enums.product.ProductCategory;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class NewProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    private String productName;

    private Double productPrice;

    // other fields...
}
