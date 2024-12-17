package com.nju.mystore.po;

import com.nju.mystore.vo.UserVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    // 设置为默认电话
    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "password")
    private String password;





    public UserVO toVO() {
        UserVO userVO = new UserVO();
        userVO.setId(this.id);
        userVO.setPhone(this.phone);
        userVO.setPassword(this.password);
        return userVO;
    }
}
