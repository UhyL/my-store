package com.nju.mystore.serviceImpl;

import com.nju.mystore.po.OrderInfo;
import com.nju.mystore.repository.OrderInfoRepository;
import com.nju.mystore.service.OrderService;
import com.nju.mystore.vo.OrderInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    public Boolean deleteProduct(Integer orderId, Integer productId) {
        Map<Integer, Integer> products = orderInfoRepository.findById(orderId).get().getProducts();
        products.remove(productId);
        orderInfoRepository.updateProductQuantity(productId, products);
        return true;
    }

    @Override
    public Boolean updateProduct(Integer orderId, Integer productId, Integer quantity) {
        Map<Integer, Integer> products = orderInfoRepository.findById(orderId).get().getProducts();
        products.put(productId, quantity);
        orderInfoRepository.updateProductQuantity(productId, products);
        return true;
    }
}
