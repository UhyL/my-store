package com.nju.mystore.serviceImpl;

import com.nju.mystore.enums.OrderStatusEnum;
import com.nju.mystore.exception.MyStoreException;
import com.nju.mystore.po.Notice;
import com.nju.mystore.po.OrderInfo;
import com.nju.mystore.po.User;
import com.nju.mystore.po.AddressInfo;
import com.nju.mystore.po.product.CartItem;
import com.nju.mystore.repository.NoticeRepository;
import com.nju.mystore.repository.OrderInfoRepository;
import com.nju.mystore.repository.UserRepository;
import com.nju.mystore.repository.AddressInfoRepository;
import com.nju.mystore.repository.product.CartItemRepository;
import com.nju.mystore.service.UserService;
import com.nju.mystore.util.SecurityUtil;
import com.nju.mystore.util.TokenUtil;
import com.nju.mystore.vo.NoticeVO;
import com.nju.mystore.vo.OrderInfoVO;
import com.nju.mystore.vo.UserVO;
import com.nju.mystore.vo.AddressInfoVO;
import com.nju.mystore.vo.product.CartItemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CartItemRepository cartItemRepository;

    private final AddressInfoRepository addressInfoRepository;

    private final OrderInfoRepository orderInfoRepository;

    private final NoticeRepository noticeRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    SecurityUtil securityUtil;


    @Override
    public Boolean register(UserVO userVO) {
        User user = userRepository.findByPhone(userVO.getPhone());
        if (user != null) {
            throw MyStoreException.phoneAlreadyExists();
        }
        User newUser = userVO.toPO();
        userRepository.save(newUser);
        return true;
    }

    @Override
    public Integer login(UserVO userVO) {
        User user = userRepository.findByPhoneAndPassword(userVO.getPhone(), userVO.getPassword());
        if (user == null) {
            throw MyStoreException.phoneOrPasswordError();
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
    public Boolean addCartItem(CartItemVO cartItemVO) {
        cartItemRepository.save(cartItemVO.toPO());
        return true;
    }

    @Override
    public List<CartItemVO> getCartItems(Integer userId) {
        List<CartItem> curr_cartItems = cartItemRepository.findByUserId(userId);
        return curr_cartItems.stream().map(CartItem::toVO).collect(Collectors.toList());
    }

    @Override
    public Boolean updateCartItem(CartItemVO cartItemVO) {
        cartItemRepository.deleteCartItem(cartItemVO.getCartItemId());
        cartItemRepository.save(cartItemVO.toPO());
        return true;
    }

    @Override
    public Boolean deleteCartItem(Integer cartItemId) {
        cartItemRepository.deleteCartItem(cartItemId);
        return true;
    }

    @Override
    public List<AddressInfoVO> getAddressInfo(Integer userId) {
        List<AddressInfo> addressInfos = addressInfoRepository.findByUserId(userId);
        return addressInfos.stream().map(AddressInfo::toVO).collect(Collectors.toList());

    }

    @Override
    public Boolean addAddressInfo(AddressInfoVO addressInfoVO) {
        addressInfoRepository.save(addressInfoVO.toPO());
        return true;
    }

    @Override
    public Boolean updateAddressInfo(AddressInfoVO addressInfoVO) {
        addressInfoRepository.deleteByAddressInfoId(addressInfoVO.getAddressInfoId());
        addressInfoRepository.save(addressInfoVO.toPO());
        return true;
    }

    @Override
    public Boolean deleteAddressInfo(AddressInfoVO addressInfoVO) {
        addressInfoRepository.delete(addressInfoVO.toPO());
        return true;
    }

    @Override
    public OrderInfoVO cartItemToOrder(Integer userId, Integer addressInfoId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        OrderInfo orderInfo = new OrderInfo();
        double totalPrice = 0;
        for(CartItem cartItem : cartItems) {
            orderInfo.getProducts().put(cartItem.getCartItemId(), cartItem.getQuantity());
            totalPrice += cartItem.getProduct().getProductPrice();
        }
        orderInfo.setOrderStatus(OrderStatusEnum.UNPAID);
        orderInfo.setUserId(userId);
        orderInfo.setTotalPrice(totalPrice);
        orderInfo.setCreateDate(new Date());
        OrderInfoVO orderInfoVO = orderInfo.toVO();
        orderInfoRepository.save(orderInfo);
        return orderInfoVO;
    }

    @Override
    public List<NoticeVO> getAllNotices(Integer userId) {
        List<Notice> notices = noticeRepository.findByUserId(userId);
        return notices.stream().map(Notice::toVO).collect(Collectors.toList());
    }
}
