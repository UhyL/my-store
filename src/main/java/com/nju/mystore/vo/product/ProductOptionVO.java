package com.nju.mystore.vo.product;

import com.nju.mystore.po.product.ProductOption;
import lombok.Data;

@Data
public class ProductOptionVO {

    private Integer productOptionId;

    private Integer productId;

    private String productOptionName;

    public ProductOption toPO() {
        ProductOption productOption = new ProductOption();
        productOption.setProductOptionId(this.productOptionId);
        productOption.setProductId(this.productId);
        productOption.setProductOptionName(this.productOptionName);
        return productOption;
    }
}
