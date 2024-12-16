package com.nju.mystore.repository.product;

import com.nju.mystore.po.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {

    List<Image> findByBelongId(Integer belongId);

}
