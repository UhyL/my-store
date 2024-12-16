package com.nju.mystore.po.product;


import com.nju.mystore.vo.product.CommentVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer commentId;

    private Integer userId;

    private Integer productId;

    private String content;

    private Date createTime;

    public CommentVO toVO() {
        CommentVO commentVO = new CommentVO();
        commentVO.setCommentId(this.commentId);
        commentVO.setUserId(this.userId);
        commentVO.setContent(this.content);
        commentVO.setProductId(this.productId);
        commentVO.setCreateTime(this.createTime);
        return commentVO;
    }
}
