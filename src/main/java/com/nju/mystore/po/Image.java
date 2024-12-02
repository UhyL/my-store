package com.nju.mystore.po;

import com.nju.mystore.enums.ImageBelongEnum;
import com.nju.mystore.vo.ImageVO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Image {
    /**
     * 图片id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "image_id")
    private int imageId;

    /**
     * 图片属于店铺还是商品
     */
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "image_belong")
    private ImageBelongEnum imageBelong;

    /**
     * 图片所属的店铺或商品的id
     */
    @Basic
    @Column(name = "belong_id")
    private Integer belongId;

    /**
     * 图片存在阿里云上的链接
     */
    @Basic
    @Column(name = "oss_url")
    private String ossUrl;


    public ImageVO toVO(){
        ImageVO imageVO = new ImageVO();
        imageVO.setImageId(this.imageId);
        imageVO.setImageBelong(this.imageBelong);
        imageVO.setBelongId(this.belongId);
        imageVO.setOssUrl(this.ossUrl);
        return imageVO;
    }

}
