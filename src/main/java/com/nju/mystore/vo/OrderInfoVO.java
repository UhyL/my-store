package com.nju.mystore.vo;

import com.nju.mystore.enums.OrderStatusEnum;
import com.nju.mystore.po.OrderInfo;
import com.nju.mystore.vo.product.CartItemVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class OrderInfoVO {

    private Integer orderInfoId;

    private Integer userId;

    private Map<Integer, Integer> products;

    private Integer addressInfoId;

    private OrderStatusEnum orderStatus;

    private double totalPrice;

    Date createDate;

    public OrderInfo toPO() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderInfoId(this.orderInfoId);
        orderInfo.setUserId(this.userId);
        orderInfo.setProducts(this.products);
        orderInfo.setAddressInfoId(this.addressInfoId);
        orderInfo.setOrderStatus(this.orderStatus);
        orderInfo.setTotalPrice(this.totalPrice);
        orderInfo.setCreateDate(this.createDate);
        return orderInfo;
    }
}
