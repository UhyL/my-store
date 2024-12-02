package com.nju.mystore.serviceImpl.calculateCouponStrategy;

import com.nju.mystore.po.Coupon;

public interface CalculateStrategy {
    Double calculate(Double price, Coupon coupon);
}
