package com.nju.mystore.controller;

import com.nju.mystore.Log.Log;
import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.po.User;
import com.nju.mystore.service.InvoiceService;
import com.nju.mystore.service.UserService;
import com.nju.mystore.util.SecurityUtil;
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

//    @Autowired
//    InvoiceService invoiceService;

    @Autowired
    private SecurityUtil securityUtil;

    @PostMapping("/register")
    public ResultVO<Boolean> register(@RequestBody UserVO userVO) {
        Log.record_log("register" + " " + "userName=" + userVO.getPhone());
        return ResultVO.buildSuccess(userService.register(userVO));
    }

    @PostMapping("/login")
    public ResultVO<Integer> login(@RequestParam("phone") String phone, @RequestParam("password") String password) {
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



    @GetMapping("/getshoppingCart/{userId}")
    public  ResultVO<List<CartItemVO>> getShoppingCart(@PathVariable("userId") Integer userId) {
        return ResultVO.buildSuccess(userService.getCartItems(userId));
    }

    @PostMapping("/addCartItem/{userId}")
    public  ResultVO<Boolean> addCartItem(@PathVariable("userId") Integer userId, @RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity) {
        return ResultVO.buildSuccess(userService.addCartItem(userId, productId, quantity));
    }

}

