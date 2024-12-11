package com.nju.mystore.repository.product;

import com.nju.mystore.po.product.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CartItemRepository  extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem cartItem WHERE cartItem.cartItemId = :cartItem_id")
    void deleteCartItem(@Param("cartItem_id") Integer cartItemId);
}
