package com.nju.mystore.repository;

import com.nju.mystore.po.CouponGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CouponGroupRepository extends JpaRepository<CouponGroup,Integer> {

    List<CouponGroup> findByCouponGroupStoreId(Integer couponGroupStoreId);

    @Modifying
    @Transactional
    @Query("UPDATE CouponGroup c SET c.couponGroupRemains = :remains WHERE c.couponGroupId = :coupon_group_id")
    void updateCouponGroupRemains(@Param("coupon_group_id") Integer couponGroupId, @Param("remains") Integer couponGroupRemains);

    @Modifying
    @Transactional
    @Query("UPDATE CouponGroup c SET c.couponGroupReceiveNum = :receive_num WHERE c.couponGroupId = :coupon_group_id")
    void updateCouponGroupReceiveNum(@Param("coupon_group_id") Integer couponGroupId, @Param("receive_num") Integer couponGroupReceiveNum);

}
