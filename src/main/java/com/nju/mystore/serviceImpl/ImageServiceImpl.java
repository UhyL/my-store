package com.nju.mystore.serviceImpl;

import com.nju.mystore.enums.ImageBelongEnum;
import com.nju.mystore.exception.BlueWhaleException;
import com.nju.mystore.po.Image;
import com.nju.mystore.po.Product;
import com.nju.mystore.po.Store;
import com.nju.mystore.repository.ImageRepository;
import com.nju.mystore.repository.ProductRepository;
import com.nju.mystore.repository.StoreRepository;
import com.nju.mystore.service.ImageService;
import com.nju.mystore.util.OssUtil;
import com.nju.mystore.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    OssUtil ossUtil;

    @Override
    public String uploadImage(ImageVO imageVO, MultipartFile file) {
        try {
            if (imageVO.getImageBelong().equals(ImageBelongEnum.STORE)) {
                Store store = storeRepository.findByStoreId(imageVO.getBelongId());
                if (store == null) {
                    throw BlueWhaleException.storeNotFound();
                }

            } else if (imageVO.getImageBelong().equals(ImageBelongEnum.PRODUCT)) {
                Product product = productRepository.findByProductId(imageVO.getBelongId());
                if (product == null) {
                    throw BlueWhaleException.productNotFound();
                }
            }
            String url = ossUtil.upload(file.getOriginalFilename(), file.getInputStream());
            imageVO.setOssUrl(url);
            imageRepository.save(imageVO.toPO());
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            throw BlueWhaleException.fileUploadFail();
        }
    }

    @Override
    public List<ImageVO> downloadImage(ImageBelongEnum imageBelong, int id) {
        return imageRepository.findAllByImageBelongAndBelongId(imageBelong, id).stream().map(Image::toVO).collect(Collectors.toList());
    }

    @Override
    public String upload(MultipartFile file) {
        try {
            return ossUtil.upload(file.getOriginalFilename(), file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            throw BlueWhaleException.fileUploadFail();
        }
    }

}
