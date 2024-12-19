package com.nju.mystore.po;

import com.nju.mystore.enums.UserRoleEnum;
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

    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    // 设置为默认电话
    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "relatedId")
    private Integer related_id;


    public UserVO toVO() {
        UserVO userVO = new UserVO();
        userVO.setId(this.id);
        userVO.setPhone(this.phone);
        userVO.setPassword(this.password);
        userVO.setRelated_id(this.related_id);
        userVO.setRole(this.role);
        return userVO;
    }
}
