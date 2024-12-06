package com.nju.mystore.vo.product;

import com.nju.mystore.enums.product.ProductCategory;
import com.nju.mystore.po.product.NewProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
public class NewProductVO {

    private Integer productId;

    private ProductCategory productCategory;

    private String productName;

    private Double productPrice;


    public NewProduct toPO() {
        NewProduct newProduct = new NewProduct();
        newProduct.setProductId(this.productId);
        newProduct.setProductCategory(this.productCategory);
        newProduct.setProductName(this.productName);
        newProduct.setProductPrice(this.productPrice);
        return newProduct;
    }
}
