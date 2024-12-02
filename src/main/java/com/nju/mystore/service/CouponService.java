package com.nju.mystore.service;

import com.nju.mystore.vo.CouponGroupVO;
import com.nju.mystore.vo.CouponVO;

import javax.transaction.Transactional;
import java.util.List;

public interface CouponService {
    Integer createCouponGroup(Integer num, CouponVO couponVO);

    Integer createCoupon(CouponVO couponVO, Integer couponGroupId);

    List<CouponGroupVO> getAllCouponGroups();

    List<CouponGroupVO> getAllCouponGroupsByStoreId(Integer storeId);

    List<CouponVO> getAllCouponsByUserId();

    List<CouponVO> getAllCouponsByInvoice(Integer invoiceId);

    @Transactional
    Integer addCoupon(Integer invoiceId, Integer couponId);

    @Transactional
    Integer receiveCoupon(Integer couponId);

}
