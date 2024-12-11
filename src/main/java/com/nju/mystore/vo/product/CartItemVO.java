package com.nju.mystore.vo.product;

import com.nju.mystore.po.Product;
import com.nju.mystore.po.product.CartItem;
import com.nju.mystore.vo.ProductVO;
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

    private Product product;

    private Date cartItemDate;

    public CartItem toPO() {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(this.cartItemId);
        cartItem.setUserId(this.userId);
        cartItem.setQuantity(this.quantity);
        cartItem.setCartItemDate(this.cartItemDate);
        cartItem.setProduct(this.product);
        return cartItem;
    }
}
