package com.nju.mystore.vo;

import com.nju.mystore.po.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private Integer id;


    private String phone;

    private String password;



    public User toPO(){
        User user=new User();
        user.setId(this.id);
        user.setPhone(this.phone);
        user.setPassword(this.password);
        return user;
    }
}
