package com.nju.mystore.serviceImpl;

import com.nju.mystore.enums.ImageBelongEnum;
import com.nju.mystore.exception.BlueWhaleException;
import com.nju.mystore.po.Image;
import com.nju.mystore.po.Store;
import com.nju.mystore.repository.ImageRepository;
import com.nju.mystore.repository.StoreRepository;
import com.nju.mystore.service.StoreService;
import com.nju.mystore.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    ImageRepository imageRepository;

    /**
     * 创建商店
     *
     * @param storeVO 前端传输商店类
     * @return 创建成功返回true，失败报错
     */
    @Override
    public Integer createStore(StoreVO storeVO) {
        Store store = storeRepository.findByStoreName(storeVO.getStoreName());
        if (store != null) {
            throw BlueWhaleException.storeNameAlreadyExists();
        }

        Store newStore = storeVO.toPO();
        storeRepository.save(newStore);

        Image image = new Image();
        image.setImageBelong(ImageBelongEnum.STORE);
        image.setBelongId(newStore.getStoreId());
        image.setOssUrl(newStore.getStoreImageUrl());
        imageRepository.save(image);
        return newStore.getStoreId();
    }

    /**
     * 通过商店id查询商店
     *
     * @param storeId 商店id
     * @return 查询成功返回前端商店类，查询失败报错
     */
    @Override
    public StoreVO getStoreById(Integer storeId) {
        Store store = storeRepository.findByStoreId(storeId);
        if (store == null) {
            throw BlueWhaleException.storeNotFound();
        }
        return store.toVO();
    }

    @Override
    public String getStoreAddress(Integer storeId) {
        Store store = storeRepository.findByStoreId(storeId);
        return store.getStoreAddress();
    }

    @Override
    public List<StoreVO> getAllStores() {
        List<Store> list = storeRepository.findAll();
        return list.stream().map(Store::toVO).collect(Collectors.toList());
    }

}
