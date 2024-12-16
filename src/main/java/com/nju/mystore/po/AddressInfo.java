package com.nju.mystore.po;


import com.nju.mystore.vo.AddressInfoVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AddressInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    private Integer addressInfoId;

    private Integer userId;

    // 省市区
    @ElementCollection
    @CollectionTable(name = "area_Address", joinColumns = @JoinColumn(name = "addressInfo_id"))
    private List<String> areaAddress;

    // 详细地址
    private String detailAddress;

    // 电话号码
    private String phoneNumber;

    //收件人
    private String receiver;

    // 是否为默认地址
    boolean isDefault;

    public AddressInfoVO toVO() {
        AddressInfoVO addressInfoVO = new AddressInfoVO();
        addressInfoVO.setAddressInfoId(this.addressInfoId);
        addressInfoVO.setUserId(this.userId);
        addressInfoVO.setAreaAdress(this.areaAddress);
        addressInfoVO.setDetailAddress(this.detailAddress);
        addressInfoVO.setPhoneNumber(this.phoneNumber);
        addressInfoVO.setReceiver(this.receiver);
        addressInfoVO.setDefault(this.isDefault);

        return addressInfoVO;
    }


}
