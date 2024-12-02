package com.nju.mystore.repository;


import com.nju.mystore.enums.CouponStatusEnum;
import com.nju.mystore.po.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Query("SELECT i from Coupon i WHERE i.couponGroupId = :coupon_group_id and i.couponStatus = :coupon_status")
    List<Coupon> findStatusAllByCouponGroupId(@Param("coupon_group_id") Integer couponGroupId, @Param("coupon_status") CouponStatusEnum couponStatus);

    @Modifying
    @Transactional
    @Query("UPDATE Coupon coupon SET coupon.couponStatus = :status WHERE coupon.couponId = :coupon_id")
    void updateCouponStatus(@Param("coupon_id") Integer couponId, @Param("status") CouponStatusEnum status);
}
