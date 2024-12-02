package com.nju.mystore.service;


import com.nju.mystore.vo.StoreVO;

import java.util.List;

public interface StoreService {
    Integer createStore(StoreVO storeVO);

    StoreVO getStoreById(Integer storeId);

    String getStoreAddress(Integer storeId);

    List<StoreVO> getAllStores();

}
