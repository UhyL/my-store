package com.nju.mystore.service;

import com.nju.mystore.vo.UserVO;
import com.nju.mystore.vo.product.CartItemVO;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    Boolean register(UserVO userVO);

    Integer login(UserVO userVO);

    UserVO getInformation();

    Boolean addCartItem(Integer userId, Integer productId, Integer quantity);

    List<CartItemVO> getCartItems(Integer userId);


}
