package com.nju.mystore.repository;

import com.nju.mystore.enums.ImageBelongEnum;
import com.nju.mystore.po.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {

    @Query(value = "select i from Image i where i.imageBelong=?1 and i.belongId=?2")
    List<Image> findAllByImageBelongAndBelongId(ImageBelongEnum imageBelong, int belongId);

}
