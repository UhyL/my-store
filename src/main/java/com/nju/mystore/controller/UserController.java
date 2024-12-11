package com.nju.mystore.controller;

import com.nju.mystore.Log.Log;
import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.po.User;
import com.nju.mystore.service.CouponService;
import com.nju.mystore.service.InvoiceService;
import com.nju.mystore.service.UserService;
import com.nju.mystore.util.SecurityUtil;
import com.nju.mystore.vo.CouponVO;
import com.nju.mystore.vo.InvoiceVO;
import com.nju.mystore.vo.ResultVO;
import com.nju.mystore.vo.UserVO;
import com.nju.mystore.vo.product.CartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    CouponService couponService;
    @Autowired
    private SecurityUtil securityUtil;

    @PostMapping("/register")
    public ResultVO<Boolean> register(@RequestBody UserVO userVO) {
        Log.record_log("register" + " " + "userName=" + userVO.getName());
        return ResultVO.buildSuccess(userService.register(userVO));
    }

    @PostMapping("/login")
    public ResultVO<String> login(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        Log.record_log("login" + " "
                + "phone=" + phone + " "
                + "password=" + password);
        return ResultVO.buildSuccess(userService.login(phone, password));
    }

    @GetMapping
    public ResultVO<UserVO> getInformation() {
        Log.record_log("getInformation");
        return ResultVO.buildSuccess(userService.getInformation());
    }

    @PostMapping
    public ResultVO<Boolean> updateInformation(@RequestBody UserVO userVO) {
        Log.record_log("updateInformation" + " " + "userId=" + userVO.getId());
        return ResultVO.buildSuccess(userService.updateInformation(userVO));
    }

    @GetMapping("/getAllInvoices")
    public ResultVO<List<InvoiceVO>> getAllInvoicesByUserId() {
        User user = securityUtil.getCurrentUser();
        Log.record_log("getAllInvoicesByUserId" + " " + "userId=" + user.getId());

        if (user.getRole().equals(RoleEnum.STAFF)) {
            return ResultVO.buildSuccess(invoiceService.getAllByInvoiceStoreId(user.getStoreId()));
        } else if (user.getRole().equals(RoleEnum.CUSTOMER)) {
            return ResultVO.buildSuccess(invoiceService.getAllByInvoiceUserId(user.getId()));
        } else {
            return ResultVO.buildSuccess(invoiceService.getAllInvoices());
        }
    }

    @GetMapping("/getAllCoupons")
    public ResultVO<List<CouponVO>> getAllCoupon() {
        User user = securityUtil.getCurrentUser();
        Log.record_log("getAllCoupon" + " " + "userId=" + user.getId());
        return ResultVO.buildSuccess(couponService.getAllCouponsByUserId());
    }

    @GetMapping("/getAllPhones")
    public ResultVO<List<String>> getAllPhones() {
        User user = securityUtil.getCurrentUser();
        Log.record_log("getAllPhones" + " " + "userId=" + user.getId());
        return ResultVO.buildSuccess(userService.getAllPhones());
    }

    @GetMapping("/getAllAddresses")
    public ResultVO<List<String>> getAllAddresses() {
        User user = securityUtil.getCurrentUser();
        Log.record_log("getAllAddresses" + " " + "userId=" + user.getId());
        return ResultVO.buildSuccess(userService.getAllAddresses());
    }

    @PostMapping("/updateAddress")
    public ResultVO<String> updateAddress(@RequestParam("address") String address) {
        User user = securityUtil.getCurrentUser();
        Log.record_log("updateAddress" + " " + "userId=" + user.getId());
        return ResultVO.buildSuccess(userService.updateAddress(address));
    }

    @PostMapping("/addPhone")
    public ResultVO<String> addPhone(@RequestParam("phone") String phone) {
        return ResultVO.buildSuccess(userService.addPhone(phone));
    }

    @PostMapping("/addAddress")
    public ResultVO<String> addAddress(@RequestParam("address") String address) {
        User user = securityUtil.getCurrentUser();
        Log.record_log("addAddress" + " " + "userId=" + user.getId());
        return ResultVO.buildSuccess(userService.addAddress(address));
    }

    @GetMapping("/getshoppingCart/{userId}")
    public  ResultVO<List<CartItemVO>> getshoppingCart(@PathVariable("userId") Integer userId) {
        return ResultVO.buildSuccess(userService.getCartItems(userId));
    }

    @PostMapping("/addCartItem/{userId}")
    public  ResultVO<Boolean> addCartItem(@PathVariable("userId") Integer userId, @RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity) {
        return ResultVO.buildSuccess(userService.addCartItem(userId, productId, quantity));
    }

}

