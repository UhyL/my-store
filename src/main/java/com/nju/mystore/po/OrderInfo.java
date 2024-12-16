package com.nju.mystore.po;


import com.nju.mystore.enums.OrderStatusEnum;
import com.nju.mystore.vo.OrderInfoVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderInfo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer orderInfoId;

    private Integer userId;

    @ElementCollection
    @CollectionTable(name = "products", joinColumns = @JoinColumn(name = "orderInfo_id"))
    @MapKeyColumn(name = "prodict_id")
    @Column(name = "quantity")
    Map<Integer, Integer> products; // product_id & quantity

    private Integer addressInfoId;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    private double totalPrice;

    Date createDate;

    public OrderInfoVO toVO() {
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        orderInfoVO.setOrderInfoId(this.orderInfoId);
        orderInfoVO.setUserId(this.userId);
        orderInfoVO.setProducts(this.products);
        orderInfoVO.setAddressInfoId(this.addressInfoId);
        orderInfoVO.setOrderStatus(this.orderStatus);
        orderInfoVO.setTotalPrice(this.totalPrice);
        orderInfoVO.setCreateDate(this.createDate);
        return orderInfoVO;
    }
}
