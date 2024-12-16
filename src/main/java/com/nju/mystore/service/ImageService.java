package com.nju.mystore.service;

import com.nju.mystore.vo.AddressInfoVO;

import java.util.List;

public interface ImageService {


    List<AddressInfoVO.ImageVO> downloadImage(Integer belongId);

}
