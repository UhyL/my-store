package com.nju.mystore.po.product;

import lombok.Data;

import javax.persistence.*;

/**
 * 商品-属性值表 存储商品的属性值，支持动态扩展
 */
@Data
@Entity
public class ProductAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private NewProduct product; // 关联商品

    @ManyToOne
    private ProductAttribute productAttribute; // 关联属性定义

    private String value; // 属性值（如甜味、冬装等）
}
