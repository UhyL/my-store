package com.nju.mystore.repository;

import com.nju.mystore.enums.OrderStatusEnum;
import com.nju.mystore.po.OrderInfo;
import com.nju.mystore.po.product.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {
    List<OrderInfo> findByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM OrderInfo orderInfo WHERE orderInfo.orderInfoId = :orderInfo_id")
    void deleteByOrderInfoId(@Param("orderInfo_id") Integer orderInfo_id);

    @Modifying
    @Transactional
    @Query("UPDATE OrderInfo orderInfo SET orderInfo.orderStatus =:status WHERE orderInfo.orderInfoId = :orderInfo_id")
    void updateOrderStatus(@Param("orderInfo_id") Integer orderInfo_id, @Param("status") OrderStatusEnum status);

    @Modifying
    @Transactional
    @Query("UPDATE OrderInfo oi SET oi.products = :products WHERE oi.orderInfoId = :orderInfoId")
    void updateProductQuantity(@Param("orderInfoId") Integer orderInfoId, @Param("products") List<CartItem> products);
}
