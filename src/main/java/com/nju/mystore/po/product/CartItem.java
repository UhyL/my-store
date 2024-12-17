package com.nju.mystore.po.product;


import com.nju.mystore.vo.product.CartItemVO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
public class CartItem {
   @GeneratedValue(strategy = GenerationType.IDENTITY)

   @Id
    private Integer  cartItemId;

    private Integer userId;

    private Integer quantity;

    @ManyToOne
    private NewProduct product;

    @OneToMany
    private List<ProductOptionValue> productOptionValues;

    private Date cartItemDate;

    public CartItemVO toVO() {
     CartItemVO cartItemVO = new CartItemVO();
     cartItemVO.setCartItemId(this.cartItemId);
     cartItemVO.setUserId(this.userId);
     cartItemVO.setQuantity(this.quantity);
     cartItemVO.setCartItemDate(this.cartItemDate);
     cartItemVO.setProduct(this.product);
     cartItemVO.setProductOptionValues(this.productOptionValues);
     return cartItemVO;
    }
}
