package com.nju.mystore.vo;

import com.nju.mystore.enums.ImageBelongEnum;
import com.nju.mystore.po.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageVO {
    /**
     * 图片id
     */

    private int imageId;

    /**
     * 图片属于店铺还是商品
     */

    private ImageBelongEnum imageBelong;

    /**
     * 图片所属的店铺或商品的id
     */

    private Integer belongId;

    /**
     * 图片存在阿里云上的链接
     */
    private String ossUrl;

    public Image toPO(){
        Image image = new Image();
        image.setImageId(this.imageId);
        image.setImageBelong(this.imageBelong);
        image.setBelongId(this.belongId);
        image.setOssUrl(this.ossUrl);
        return image;
    }
}
