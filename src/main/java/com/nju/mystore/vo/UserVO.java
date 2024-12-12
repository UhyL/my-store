package com.nju.mystore.vo;

import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.po.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private Integer id;


    private String phone;

    private String password;


    private String address;

    private RoleEnum role;

    private Date createTime;

    private List<String> phones;

    private List<String> addresses;

    private List<Integer> coupons;

    public User toPO(){
        User user=new User();
        user.setId(this.id);
        user.setPhone(this.phone);
        user.setRole(this.role);
        user.setPassword(this.password);
        return user;
    }
}
