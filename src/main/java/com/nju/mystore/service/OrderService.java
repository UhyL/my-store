package com.nju.mystore.service;

import com.nju.mystore.vo.OrderInfoVO;
import com.nju.mystore.vo.product.CartItemVO;

import java.util.List;

public interface OrderService {

    List<OrderInfoVO> getAllOrders(Integer userId);

    Boolean createOrder(OrderInfoVO orderInfoVO);

    Boolean deleteOrder(Integer orderId);


    Boolean deleteProduct(Integer orderId, CartItemVO cartItemVO);

    Boolean updateProduct(Integer orderId, CartItemVO cartItemVO);
}
