package com.nju.mystore.vo;

import com.nju.mystore.enums.UserRoleEnum;
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

    private UserRoleEnum role;

    private String phone;

    private String password;

    private Integer related_id;

    public User toPO(){
        User user=new User();
        user.setId(this.id);
        user.setPhone(this.phone);
        user.setPassword(this.password);
        user.setRelated_id(this.related_id);
        user.setRole(this.role);
        return user;
    }
}
