package com.nju.mystore.vo.product;

import com.nju.mystore.po.product.NewProduct;
import com.nju.mystore.po.product.ProductAttribute;
import com.nju.mystore.po.product.ProductAttributeValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@NoArgsConstructor
public class ProductAttributeValueVO {

    private Integer id;

    private NewProduct product; // 关联商品

    private ProductAttribute productAttribute; // 关联属性定义

    private String value; // 属性值（如甜味、冬装等）

    public ProductAttributeValue toPO() {
        ProductAttributeValue productAttributeValue = new ProductAttributeValue();
        productAttributeValue.setId(this.id);
        productAttributeValue.setProduct(this.product);
        productAttributeValue.setProductAttribute(this.productAttribute);
        productAttributeValue.setValue(this.value);
        return productAttributeValue;
    }

}
