package com.nju.mystore.po.product;

import com.nju.mystore.vo.product.ProductAttributeVO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 属性定义表 存储每种类别允许的属性
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productAttributeId;

    private String productAttributeName; // 属性名，如食品的口味 flavor，服饰的季节 season

    private String displayName; // 显示名，如"口味", "季节"

    private String productCategory; // 所属类别, 属于食品还是服饰

    public ProductAttributeVO toVO() {
        ProductAttributeVO productAttributeVO = new ProductAttributeVO();
        productAttributeVO.setProductAttributeId(this.productAttributeId);
        productAttributeVO.setProductAttributeName(this.productAttributeName);
        productAttributeVO.setDisplayName(this.displayName);
        productAttributeVO.setProductCategory(this.productCategory);
        return productAttributeVO;
    }


}
