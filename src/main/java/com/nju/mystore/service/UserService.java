package com.nju.mystore.service;

import com.nju.mystore.vo.UserVO;
import com.nju.mystore.vo.product.CartItemVO;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    Boolean register(UserVO userVO);

    String login(String phone, String password);

    UserVO getInformation();

    Boolean updateInformation(UserVO userVO);

    List<String> getAllPhones();

    List<String> getAllAddresses();

    Boolean addCartItem(Integer userId, Integer productId, Integer quantity);

    List<CartItemVO> getCartItems(Integer userId);

    String updateAddress(String address);

    @Transactional
    String addPhone(String phone);

    @Transactional
    String addAddress(String address);



}
