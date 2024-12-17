package com.nju.mystore.vo.product;

import com.nju.mystore.po.product.CartItem;
import com.nju.mystore.po.product.NewProduct;
import com.nju.mystore.po.product.ProductOptionValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class CartItemVO {

    private Integer cartItemId;

    private Integer userId;

    private Integer quantity;

    private NewProduct product;

    private List<ProductOptionValue> productOptionValues;

    private Date cartItemDate;

    public CartItem toPO() {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(this.cartItemId);
        cartItem.setUserId(this.userId);
        cartItem.setQuantity(this.quantity);
        cartItem.setCartItemDate(this.cartItemDate);
        cartItem.setProduct(this.product);
        cartItem.setProductOptionValues(this.productOptionValues);
        return cartItem;
    }
}
