package com.nju.mystore.serviceImpl;

import com.nju.mystore.exception.BlueWhaleException;
import com.nju.mystore.po.User;
import com.nju.mystore.po.product.CartItem;
import com.nju.mystore.repository.UserRepository;
import com.nju.mystore.repository.product.CartItemRepository;
import com.nju.mystore.service.UserService;
import com.nju.mystore.util.SecurityUtil;
import com.nju.mystore.util.TokenUtil;
import com.nju.mystore.vo.UserVO;
import com.nju.mystore.vo.product.CartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

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
    CartItemRepository cartItemRepository;

//    @Autowired
//    ProductRepository productRepository;

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
        userRepository.save(newUser);
        return true;
    }

    @Override
    public Integer login(String phone, String password) {
        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null) {
            throw BlueWhaleException.phoneOrPasswordError();
        }
        return user.getId();
    }

    @Override
    @Transactional
    public UserVO getInformation() {
        User user = securityUtil.getCurrentUser();
        return user.toVO();
    }


    @Override
    public Boolean addCartItem(Integer userId, Integer productId, Integer quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setQuantity(quantity);
        //Product product = productRepository.findByProductId(productId);
        //cartItem.setProduct(product);
        cartItemRepository.save(cartItem);
        return true;
    }

    @Override
    public List<CartItemVO> getCartItems(Integer userId) {
        List<CartItem> curr_cartItems = cartItemRepository.findByUserId(userId);
        return curr_cartItems.stream().map(CartItem::toVO).collect(Collectors.toList());
    }


}
