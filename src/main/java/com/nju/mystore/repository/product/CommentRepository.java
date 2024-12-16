package com.nju.mystore.repository.product;

import com.nju.mystore.po.product.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {


    List<Comment> findByProductId(Integer productId);
}
