package com.nju.mystore.serviceImpl;

import com.nju.mystore.po.product.Image;
import com.nju.mystore.repository.product.ImageRepository;
import com.nju.mystore.service.ImageService;
import com.nju.mystore.util.OssUtil;
import com.nju.mystore.vo.AddressInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {


    @Autowired
    ImageRepository imageRepository;

    @Autowired
    OssUtil ossUtil;

    @Override
    public List<AddressInfoVO.ImageVO> downloadImage(Integer belongId) {
        List<Image> images = imageRepository.findByBelongId(belongId);
        return images.stream().map(Image::toVO).collect(Collectors.toList());
    }
}
