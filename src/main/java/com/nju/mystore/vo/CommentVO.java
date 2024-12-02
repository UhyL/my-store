package com.nju.mystore.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentVO {
    String username;

    Integer userId;

    Integer score;

    String comment;

    Date time;

}
