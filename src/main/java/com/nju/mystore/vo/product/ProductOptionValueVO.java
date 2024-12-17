package com.nju.mystore.vo.product;

import com.nju.mystore.po.product.ProductOptionValue;
import lombok.Data;

@Data
public class ProductOptionValueVO {

    private Integer productOptionValueId;

    private Integer productId;

    private String productOptionName;

    private String value;

    public ProductOptionValue toPO() {
        ProductOptionValue productOptionValue = new ProductOptionValue();
        productOptionValue.setProductOptionValueId(this.productOptionValueId);
        productOptionValue.setProductId(this.productId);
        productOptionValue.setProductOptionName(this.productOptionName);
        productOptionValue.setValue(this.value);
        return productOptionValue;
    }
}
