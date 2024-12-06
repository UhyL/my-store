package com.nju.mystore.vo.product;

import com.nju.mystore.po.product.ProductAttribute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductAttributeVO {

    private Integer productAttributeId;

    private String productAttributeName; // 属性名，如食品的口味 flavor，服饰的季节 season

    private String displayName; // 显示名，如"口味", "季节"

    private String productCategory; // 所属类别, 属于食品还是服饰

    public ProductAttribute toPO() {
        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setProductAttributeId(this.productAttributeId);
        productAttribute.setProductAttributeName(this.productAttributeName);
        productAttribute.setDisplayName(this.displayName);
        productAttribute.setProductCategory(this.productCategory);
        return productAttribute;
    }

}
