package com.nju.mystore.vo;


import com.nju.mystore.enums.CouponEnum;
import com.nju.mystore.enums.CouponStatusEnum;
import com.nju.mystore.po.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponVO {
    private Integer couponId;

    private Integer couponGroupId;

    private CouponEnum couponCategory;

    private CouponEnum couponSpace;

    private Double full;

    private Double reduction;

    private Integer couponStoreId;

    private Date couponCreateTime;

    private  Date couponExpireTime;

    private CouponStatusEnum couponStatus;



    public Coupon toPO() {
        Coupon coupon = new Coupon();
        coupon.setCouponId(this.couponId);
        coupon.setCouponGroupId(this.couponGroupId);
        coupon.setCouponCategory(this.couponCategory);
        coupon.setCouponSpace(this.couponSpace);
        coupon.setFull(this.full);
        coupon.setReduction(this.reduction);
        coupon.setCouponStoreId(this.couponStoreId);
        coupon.setCouponCreateTime(this.couponCreateTime);
        coupon.setCouponExpireTime(this.couponExpireTime);
        coupon.setCouponStatus(this.couponStatus);
        return coupon;
    }
}
