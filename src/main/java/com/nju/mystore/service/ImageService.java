package com.nju.mystore.service;

import com.nju.mystore.enums.ImageBelongEnum;
import com.nju.mystore.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    String uploadImage(ImageVO imageVO,MultipartFile file);

    List<ImageVO> downloadImage(ImageBelongEnum imageBelong, int id);

    String upload(MultipartFile file);

}
