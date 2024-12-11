package com.nju.mystore.po;

import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.vo.UserVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    // 设置为默认电话
    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "password")
    private String password;

    //必须注意，在Java中用驼峰，在MySQL字段中用连字符_
    @Basic
    @Column(name = "create_time")
    private Date createTime;

    @Basic
    @Column(name = "store_id")
    private Integer storeId;


    // 设置为默认地址
    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    //电话
    @ElementCollection
    @CollectionTable(name = "user_phone", joinColumns = @JoinColumn(name = "user_id"))
    private List<String> phones;


    // 地址
    @ElementCollection
    @CollectionTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"))
    private List<String> addresses;


    @ElementCollection
    @CollectionTable(name = "user_coupon", joinColumns = @JoinColumn(name = "user_id"))
    private List<Integer> coupons;



    public UserVO toVO() {
        UserVO userVO = new UserVO();
        userVO.setId(this.id);
        userVO.setAddress(this.address);
        userVO.setName(this.name);
        userVO.setRole(this.role);
        userVO.setStoreId(this.storeId);
        userVO.setPhone(this.phone);
        userVO.setPassword(this.password);
        userVO.setCreateTime(this.createTime);
        userVO.setCoupons(this.coupons);
        userVO.setAddresses(this.addresses);
        userVO.setPhones(this.phones);
        return userVO;
    }
}
