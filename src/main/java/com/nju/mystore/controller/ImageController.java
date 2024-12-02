package com.nju.mystore.controller;

import com.nju.mystore.Log.Log;
import com.nju.mystore.enums.ImageBelongEnum;
import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.service.ImageService;
import com.nju.mystore.util.AccessUtil;
import com.nju.mystore.util.JsonUtil;
import com.nju.mystore.vo.ImageVO;
import com.nju.mystore.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    ImageService imageService;

    @AccessUtil(roles = {RoleEnum.STAFF, RoleEnum.MANAGER,RoleEnum.CEO})
    @PostMapping("/imagesUpdate")
    public ResultVO<String> uploadImage(@RequestPart("meg") String msg,@RequestPart MultipartFile file) throws IOException {
        ImageVO imageVO = JsonUtil.stringToObject(msg,ImageVO.class);
        Log.record_log("uploadImage");
        return ResultVO.buildSuccess(imageService.uploadImage(imageVO,file));
    }

    @GetMapping("/imagesDownload")
    public ResultVO<List<ImageVO>> downloadImage(@RequestParam("imageBelong") ImageBelongEnum imageBelong, @RequestParam int belongId){
        Log.record_log("downloadImage"+" "
                +"imageBelong="+imageBelong+" "
                +"belongId="+belongId);
        return ResultVO.buildSuccess(imageService.downloadImage(imageBelong,belongId));
    }

}
