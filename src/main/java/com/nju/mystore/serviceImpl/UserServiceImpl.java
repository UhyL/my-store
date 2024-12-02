package com.nju.mystore.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.mystore.exception.BlueWhaleException;
import com.nju.mystore.po.User;
import com.nju.mystore.repository.UserRepository;
import com.nju.mystore.service.UserService;
import com.nju.mystore.util.SecurityUtil;
import com.nju.mystore.util.TokenUtil;
import com.nju.mystore.vo.UserVO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: GaoZhaolong
 * @Date: 14:46 2023/11/26
 * <p>
 * 注册登录功能实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    SecurityUtil securityUtil;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Boolean register(UserVO userVO) {
        User user = userRepository.findByPhone(userVO.getPhone());
        if (user != null) {
            throw BlueWhaleException.phoneAlreadyExists();
        }
        User newUser = userVO.toPO();
        newUser.setPhones(Collections.singletonList(userVO.getPhone()));
        newUser.setAddresses(Collections.singletonList(userVO.getAddress()));
        newUser.setCreateTime(new Date());
        userRepository.save(newUser);
        return true;
    }

    @Override
    public String login(String phone, String password) {
        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null) {
            throw BlueWhaleException.phoneOrPasswordError();
        }
        return tokenUtil.getToken(user);
    }

    @Override
    @Transactional
    public UserVO getInformation() {
        User user = securityUtil.getCurrentUser();
        return user.toVO();
    }

    @Override
    public Boolean updateInformation(UserVO userVO) {
        User user = securityUtil.getCurrentUser();
        String key = "User" + user.getId();
        if (userVO.getPassword() != null) {
            user.setPassword(userVO.getPassword());
        }
        if (userVO.getName() != null) {
            user.setName(userVO.getName());
        }
        if (userVO.getAddress() != null) {
            user.setAddress(userVO.getAddress());
        }
        userRepository.save(user);

        return true;
    }

    @Override
    public List<String> getAllPhones() {
        User user = securityUtil.getCurrentUser();
        return user.getPhones();
    }

    @Override
    public List<String> getAllAddresses() {
        User user = securityUtil.getCurrentUser();
        return user.getAddresses();
    }

    @Override
    public String updateAddress(String address) {
        User user = securityUtil.getCurrentUser();
        userRepository.updateUserByAddress(user.getId(), address);
        return address;
    }

    @Override
    @Transactional
    public String addPhone(String phone) {
        User user = securityUtil.getCurrentUser();
        List<String> newPhones = Collections.emptyList();

        if (user.getPhones() == null) {
            newPhones = new ArrayList<>(Collections.emptyList());
        } else {
            newPhones = user.getPhones();
        }

        newPhones.add(phone);
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setPhone(user.getPhone());
        newUser.setAddress(user.getAddress());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        newUser.setStoreId(user.getStoreId());
        newUser.setCreateTime(user.getCreateTime());
        newUser.setCoupons(user.getCoupons());
        newUser.setAddresses(user.getAddresses());
        newUser.setPhones(newPhones);
        entityManager.merge(newUser);
        return phone;
    }


    @Override
    @Transactional
    public String addAddress(String address) {
        User user = securityUtil.getCurrentUser();
        List<String> newAddresses = Collections.emptyList();
        String key = "UserAddress:" + user.getId();

        if (user.getAddresses() == null) {
            newAddresses = new ArrayList<>(Collections.emptyList());
        } else {
            newAddresses = user.getAddresses();
        }

        newAddresses.add(address);
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setPhone(user.getPhone());
        newUser.setAddress(user.getAddress());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        newUser.setStoreId(user.getStoreId());
        newUser.setCreateTime(user.getCreateTime());
        newUser.setCoupons(user.getCoupons());
        newUser.setAddresses(newAddresses);
        newUser.setPhones(user.getPhones());
        entityManager.merge(newUser);

        return address;
    }
}
