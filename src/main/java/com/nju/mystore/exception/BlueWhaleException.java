package com.nju.mystore.exception;

import com.nju.mystore.Log.Log;

/**
 * @Author: DingXiaoyu
 * @Date: 0:26 2023/11/26
 * 你可以在这里自定义Exception
 */
public class BlueWhaleException extends RuntimeException {

    public BlueWhaleException(String message) {
        super(message);
    }

    private static BlueWhaleException record_exception(String str){
        BlueWhaleException exception = new BlueWhaleException(str);
        Log.record_log(exception.getMessage());
        return exception;
    }

    public static BlueWhaleException phoneAlreadyExists() {
        return record_exception("手机号已经存在!");
    }


    public static BlueWhaleException notLogin() {
        return record_exception("未登录!");
    }

    public static BlueWhaleException phoneOrPasswordError() {
        return record_exception("手机号或密码错误!");
    }

    public static BlueWhaleException fileUploadFail() {
        return record_exception("文件上传失败!");
    }

    public static BlueWhaleException storeNameAlreadyExists() {
        return record_exception("该商店名已存在!");
    }

    public static BlueWhaleException storeNotFound() {
        return record_exception("商店不存在！");
    }

    public static BlueWhaleException productAlreadyExists() {
        return record_exception("商品已存在！");
    }

    public static BlueWhaleException productNotFound() {
        return record_exception("商品不存在");
    }

    public static BlueWhaleException invoiceNotFound() {
        return record_exception("订单不存在");
    }

    public static BlueWhaleException productNotEnough() {
        return record_exception("商品库存不足");
    }

    public static BlueWhaleException couponGroupGlobal() {
        return record_exception("创建全局优惠券组权限不够");
    }

    public static BlueWhaleException couponGroupStore() {
        return record_exception("创建商店优惠券组权限不够");
    }

    public static BlueWhaleException couponNotFree() {
        return record_exception("优惠券无法领取");
    }


    public static BlueWhaleException couponNotExist() {
        return record_exception("无优惠券");
    }

    public static BlueWhaleException specialCouponNotExist() {
        return record_exception("当前无满足条件蓝鲸优惠券");
    }

    public static BlueWhaleException fullReductionCouponNotExist() {
        return record_exception("当前无满足条件满减优惠券");
    }

    public static BlueWhaleException payError() {
        return record_exception("支付失败");
    }

    public static BlueWhaleException accessDenied() {
        return new BlueWhaleException("权限不足");
    }

}

