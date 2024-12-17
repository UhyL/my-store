package com.nju.mystore.vo;

import com.nju.mystore.po.AddressInfo;
import com.nju.mystore.po.product.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddressInfoVO {

    private Integer addressInfoId;

    private Integer userId;

    private List<String> areaAddress;

    private String detailAddress;

    private String phoneNumber;

    private String receiver;

    boolean isDefault;

    public AddressInfo toPO() {
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddressInfoId(this.addressInfoId);
        addressInfo.setUserId(this.userId);
        addressInfo.setAreaAddress(this.areaAddress);
        addressInfo.setDetailAddress(this.detailAddress);
        addressInfo.setPhoneNumber(this.phoneNumber);
        addressInfo.setReceiver(this.receiver);
        addressInfo.setDefault(this.isDefault);
        return addressInfo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ImageVO {
        /**
         * 图片id
         */
        private int imageId;


        /**
         * 图片所属的商品的id
         */
        private Integer belongId;

        /**
         * 图片存在阿里云上的链接
         */
        private String ossUrl;

        public Image toPO(){
            Image image = new Image();
            image.setImageId(this.imageId);
            image.setBelongId(this.belongId);
            image.setOssUrl(this.ossUrl);
            return image;
        }
    }
}
