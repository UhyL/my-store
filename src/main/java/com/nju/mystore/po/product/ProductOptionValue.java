package com.nju.mystore.po.product;

import com.nju.mystore.vo.product.ProductOptionValueVO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductOptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productOptionValueId;

    private Integer productId;

    private String productOptionName;

    private String value;

    public ProductOptionValueVO toVO() {
        ProductOptionValueVO productOptionValueVO = new ProductOptionValueVO();
        productOptionValueVO.setProductOptionValueId(this.productOptionValueId);
        productOptionValueVO.setProductId(this.productId);
        productOptionValueVO.setProductOptionName(this.productOptionName);
        productOptionValueVO.setValue(this.value);
        return productOptionValueVO;
    }

}
