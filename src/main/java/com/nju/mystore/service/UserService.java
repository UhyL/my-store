package com.nju.mystore.service;

import com.nju.mystore.vo.NoticeVO;
import com.nju.mystore.vo.OrderInfoVO;
import com.nju.mystore.vo.UserVO;
import com.nju.mystore.vo.AddressInfoVO;
import com.nju.mystore.vo.product.CartItemVO;

import java.util.List;

public interface UserService {
    Boolean register(UserVO userVO);

    Integer login(UserVO userVO);

    UserVO getInformation();

    Boolean addCartItem(CartItemVO cartItemVO);

    List<CartItemVO> getCartItems(Integer userId);

    Boolean updateCartItem(CartItemVO cartItemVO);

    Boolean deleteCartItem(Integer cartItemId);

    List<AddressInfoVO> getAddressInfo(Integer userId);

    Boolean addAddressInfo(AddressInfoVO addressInfoVO);

    Boolean updateAddressInfo(AddressInfoVO addressInfoVO);

    Boolean deleteAddressInfo(AddressInfoVO addressInfoVO);

    OrderInfoVO cartItemToOrder(Integer userId, Integer addressInfoId);

    List<NoticeVO> getAllNotices(Integer userId);

    Integer getRelatedId(Integer userId);
}
