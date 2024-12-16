package com.nju.mystore.vo.product;


import com.nju.mystore.po.product.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class CommentVO {

    private Integer commentId;

    private Integer userId;

    private Integer productId;

    private String content;

    private Date createTime;

    public Comment toPO() {
        Comment comment = new Comment();
        comment.setCommentId(this.commentId);
        comment.setUserId(this.userId);
        comment.setContent(this.content);
        comment.setProductId(this.productId);
        comment.setCreateTime(this.createTime);
        return comment;
    }
}
