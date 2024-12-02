package com.nju.mystore.serviceImpl.calculateCouponStrategy;

import com.nju.mystore.po.Coupon;

public class FullReductionStrategy implements CalculateStrategy {

    @Override
    public Double calculate(Double price, Coupon coupon) {
        double reduction = coupon.getReduction();
        return price - reduction;
    }
}
