package com.nju.mystore.controller;

import com.nju.mystore.service.OrderService;
import com.nju.mystore.service.UserService;
import com.nju.mystore.util.SecurityUtil;
import com.nju.mystore.vo.*;
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
    OrderService orderService;

    @Autowired
    private SecurityUtil securityUtil;

    @PostMapping("/register")
    public ResultVO<Boolean> register(@RequestBody UserVO userVO) {
        return ResultVO.buildSuccess(userService.register(userVO));
    }

    @PostMapping("/login")
    public ResultVO<Integer> login(@RequestBody UserVO userVO) {
        return ResultVO.buildSuccess(userService.login(userVO));
    }

    @GetMapping
    public ResultVO<UserVO> getInformation() {
        return ResultVO.buildSuccess(userService.getInformation());
    }


    @GetMapping("/getShoppingCart/{userId}")
    public  ResultVO<List<CartItemVO>> getShoppingCart(@PathVariable("userId") Integer userId) {
        return ResultVO.buildSuccess(userService.getCartItems(userId));
    }

    @PostMapping("/addCartItem/{userId}")
    public  ResultVO<Boolean> addCartItem(@RequestBody CartItemVO cartItemVO) {
        return ResultVO.buildSuccess(userService.addCartItem(cartItemVO));
    }

    @PostMapping("/updateCartItem")
    public ResultVO<Boolean> updateCartItem(@RequestBody CartItemVO cartItemVO) {
        return ResultVO.buildSuccess(userService.updateCartItem(cartItemVO));
    }

   @PostMapping("/deleteCartItem")
   public ResultVO<Boolean> deleteCartItem(@RequestParam ("cartItemId") Integer cartItemId) {
        return ResultVO.buildSuccess(userService.deleteCartItem(cartItemId));
   }

    @GetMapping("/getAllAddressInfo/{userId}")
    public ResultVO<List<AddressInfoVO>> getAllAddressInfo(@PathVariable("userId") Integer userId) {
        return ResultVO.buildSuccess(userService.getAddressInfo(userId));
    }

    @GetMapping("/addAddressInfo")
    public ResultVO<Boolean> addAddressInfo(@RequestBody AddressInfoVO addressInfoVO) {
        return ResultVO.buildSuccess(userService.addAddressInfo(addressInfoVO));
    }

    @PostMapping("/updateAddressInfo")
    public ResultVO<Boolean> updateAddressInfo(@RequestBody AddressInfoVO addressInfoVO) {
        return ResultVO.buildSuccess(userService.updateAddressInfo(addressInfoVO));
    }
    @PostMapping("/deleteAddressInfo")
    public ResultVO<Boolean> deleteAddressInfo(@RequestBody AddressInfoVO addressInfoVO) {
        return ResultVO.buildSuccess(userService.deleteAddressInfo(addressInfoVO));
    }

    @GetMapping("/getAllOrders/{userId}")
    public ResultVO<List<OrderInfoVO>> getAllOrders(@PathVariable("userId") Integer userId) {
        return ResultVO.buildSuccess(orderService.getAllOrders(userId));
    }

    @PostMapping("/createOrder")
    public ResultVO<Boolean> createOrder(@RequestBody OrderInfoVO orderInfoVO) {
        return ResultVO.buildSuccess(orderService.createOrder(orderInfoVO));
    }

    @PostMapping("/deleteOrder")
    public ResultVO<Boolean> deleteOrder(@RequestParam ("orderId") Integer orderId) {
        return ResultVO.buildSuccess(orderService.deleteOrder(orderId));
    }

    @PostMapping("/deleteProduct")
    public ResultVO<Boolean> deleteProduct(@RequestParam("orderId") Integer orderId, @RequestBody CartItemVO cartItemVO) {
        return ResultVO.buildSuccess(orderService.deleteProduct(orderId, cartItemVO));
    }

    @PostMapping("/updateProduct")
    public ResultVO<Boolean> updateProduct(@RequestParam("orderId") Integer orderId, @RequestBody CartItemVO cartItemVO) {
        return ResultVO.buildSuccess(orderService.updateProduct(orderId,cartItemVO));
    }

    @PostMapping("/cartItemToOrder/{userId}")
    public ResultVO<OrderInfoVO> cartItemToOrder(@PathVariable("userId") Integer userId, @RequestParam("addressInfoId") Integer addressInfoId) {
        return ResultVO.buildSuccess(userService.cartItemToOrder(userId, addressInfoId));
    }

    @GetMapping("/getAllNotices/{userId}")
    public ResultVO<List<NoticeVO>> getAllNotices(@PathVariable("userId") Integer userId) {
        return ResultVO.buildSuccess(userService.getAllNotices(userId));
    }
}

