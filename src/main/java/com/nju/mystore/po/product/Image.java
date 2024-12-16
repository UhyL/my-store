package com.nju.mystore.po.product;

import com.nju.mystore.vo.AddressInfoVO;
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
     * 图片所属的商品的id
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


    public AddressInfoVO.ImageVO toVO(){
        AddressInfoVO.ImageVO imageVO = new AddressInfoVO.ImageVO();
        imageVO.setImageId(this.imageId);
        imageVO.setBelongId(this.belongId);
        imageVO.setOssUrl(this.ossUrl);
        return imageVO;
    }

}
