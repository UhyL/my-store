package com.nju.mystore.serviceImpl.calculateCouponStrategy;

import com.nju.mystore.po.Coupon;

public class SpecialStrategy implements CalculateStrategy {
    @Override
    public Double calculate(Double price, Coupon coupon) {
        double[] prices = {0, 95, 185, 270, 350, 425};
        double[] discount = {0.95, 0.9, 0.85, 0.8, 0.75, 0.7};


        int index = Math.min((int) (price / 100), 5);
        return prices[index] + (price - 100 * index) * discount[index];
    }
}
