package com.nju.mystore.controller;

import com.nju.mystore.Log.Log;
import com.nju.mystore.enums.CouponStatusEnum;
import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.po.Coupon;
import com.nju.mystore.po.User;
import com.nju.mystore.repository.CouponGroupRepository;
import com.nju.mystore.repository.CouponRepository;
import com.nju.mystore.repository.UserRepository;
import com.nju.mystore.service.CouponService;
import com.nju.mystore.util.AccessUtil;
import com.nju.mystore.util.SecurityUtil;
import com.nju.mystore.vo.CouponGroupVO;
import com.nju.mystore.vo.CouponVO;
import com.nju.mystore.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CouponGroupRepository couponGroupRepository;

    @Autowired
    SecurityUtil securityUtil;

    @AccessUtil(roles = {RoleEnum.STAFF, RoleEnum.MANAGER, RoleEnum.CEO})
    @PostMapping("/createCouponGroup")
    public ResultVO<Integer> createCouponGroup(@RequestBody CouponVO couponVO,

                                               @RequestParam("num") Integer num) {
        User user = securityUtil.getCurrentUser();
        Log.record_log("createCouponGroup" + " "
                + "userId=" + user.getId() + " "
                + "num=" + num);
        return ResultVO.buildSuccess(couponService.createCouponGroup(num, couponVO));
    }

    @GetMapping("/getAllCouponGroups")
    public ResultVO<List<CouponGroupVO>> getAllCouponGroups() {
        User user = securityUtil.getCurrentUser();
        Log.record_log("getAllCouponGroups" + " "
                + "userId=" + user.getId());
        return ResultVO.buildSuccess(couponService.getAllCouponGroups());
    }

    @GetMapping("/getAllCouponGroupsByStoreId/{storeId}")
    public ResultVO<List<CouponGroupVO>> getAllCouponGroupsByStoreId(@PathVariable("storeId") Integer storeId) {
        Log.record_log("getAllCouponGroupsByStoreId" + " "
                + "storeId=" + storeId);
        return ResultVO.buildSuccess(couponService.getAllCouponGroupsByStoreId(storeId));
    }

    @AccessUtil(roles = {RoleEnum.CUSTOMER})
    @PostMapping("/receiveCoupon")
    public synchronized ResultVO<Integer> receiveCoupon(@RequestParam("couponGroupId") Integer couponGroupId) {
        User user = securityUtil.getCurrentUser();
        List<Coupon> list = couponRepository.findStatusAllByCouponGroupId(couponGroupId, CouponStatusEnum.FREE);
        if (list.isEmpty()) {
            Log.record_log("receiveCoupon" + " " + "优惠券领取失败");
            return ResultVO.buildFailure("优惠券领取失败");
        } else {
            List<Integer> coupons = userRepository.findById(user.getId()).get().getCoupons();
            if (coupons != null) {
                for (Integer couponId : coupons) {
                    if (Objects.equals(couponRepository.findById(couponId).get().getCouponGroupId(), couponGroupId)) {
                        Log.record_log("receiveCoupon" + " " + "优惠券重复领取");
                        return ResultVO.buildFailure("优惠券重复领取");
                    }
                }
            }
            couponGroupRepository.updateCouponGroupReceiveNum(couponGroupId, couponGroupRepository.findById(couponGroupId).get().getCouponGroupReceiveNum() + 1);
            couponGroupRepository.updateCouponGroupRemains(couponGroupId, couponGroupRepository.findById(couponGroupId).get().getCouponGroupRemains() - 1);
            Coupon coupon = list.get(0);
            Log.record_log("receiveCoupon" + " " + "成功领取");
            return ResultVO.buildSuccess(couponService.receiveCoupon(coupon.getCouponId()));
        }
    }

}
