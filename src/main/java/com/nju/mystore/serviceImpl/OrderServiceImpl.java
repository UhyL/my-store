package com.nju.mystore.serviceImpl;

import com.nju.mystore.po.OrderInfo;
import com.nju.mystore.po.product.CartItem;
import com.nju.mystore.repository.OrderInfoRepository;
import com.nju.mystore.service.OrderService;
import com.nju.mystore.vo.OrderInfoVO;
import com.nju.mystore.vo.product.CartItemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderInfoRepository orderInfoRepository;

    @Override
    public List<OrderInfoVO> getAllOrders(Integer userId) {
        List<OrderInfo> orderInfos = orderInfoRepository.findByUserId(userId);
        return orderInfos.stream().map(OrderInfo::toVO).collect(Collectors.toList());
    }

    @Override
    public Boolean createOrder(OrderInfoVO orderInfoVO) {
        OrderInfo orderInfo = orderInfoVO.toPO();
        orderInfoRepository.save(orderInfo);
        return true;
    }

    @Override
    public Boolean deleteOrder(Integer orderId) {
        orderInfoRepository.deleteByOrderInfoId(orderId);
        return true;
    }

    @Override
    public Boolean deleteProduct(Integer orderId, CartItemVO cartItemVO) {
        CartItem cartItem = cartItemVO.toPO();
        List<CartItem> products = orderInfoRepository.findById(orderId).get().getProducts();
        products.remove(cartItem);
        orderInfoRepository.updateProductQuantity(orderId, products);
        return true;
    }

    @Override
    public Boolean updateProduct(Integer orderId, CartItemVO cartItemVO) {
        List<CartItem> products = orderInfoRepository.findById(orderId).get().getProducts();
        boolean flag = false;
        for(CartItem cartItem : products) {
            if(Objects.equals(cartItem.getCartItemId(), cartItemVO.getCartItemId())) {
                cartItem.setQuantity(cartItemVO.getQuantity());
                flag = true;
                break;
            }
        }

        if(!flag)
            products.add(cartItemVO.toPO());

        orderInfoRepository.updateProductQuantity(orderId, products);
        return true;
    }
}
