package com.nju.mystore.po.product;

import com.nju.mystore.vo.product.ProductOptionVO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productOptionId;

    private Integer productId;

    private String productOptionName;

    public ProductOptionVO toVO() {
        ProductOptionVO productOptionVO = new ProductOptionVO();
        productOptionVO.setProductOptionId(this.productOptionId);
        productOptionVO.setProductId(this.productId);
        productOptionVO.setProductOptionName(this.productOptionName);
        return productOptionVO;
    }
}
