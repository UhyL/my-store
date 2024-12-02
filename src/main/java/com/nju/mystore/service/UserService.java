package com.nju.mystore.service;

import com.nju.mystore.vo.UserVO;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    Boolean register(UserVO userVO);

    String login(String phone, String password);

    UserVO getInformation();

    Boolean updateInformation(UserVO userVO);

    List<String> getAllPhones();

    List<String> getAllAddresses();

    String updateAddress(String address);

    @Transactional
    String addPhone(String phone);

    @Transactional
    String addAddress(String address);

}
