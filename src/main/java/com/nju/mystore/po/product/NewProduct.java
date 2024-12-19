package com.nju.mystore.po.product;

import com.nju.mystore.enums.product.ProductCategory;
import com.nju.mystore.vo.product.NewProductVO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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

    private Double nowPrice;

    // other fields...

    public NewProductVO toVO() {
        NewProductVO newProductVO = new NewProductVO();
        newProductVO.setProductId(this.productId);
        newProductVO.setProductCategory(this.productCategory);
        newProductVO.setProductName(this.productName);
        newProductVO.setProductPrice(this.productPrice);
        newProductVO.setNowPrice(this.nowPrice);
        return newProductVO;
    }
}
