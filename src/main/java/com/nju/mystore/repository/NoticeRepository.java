package com.nju.mystore.repository;

import com.nju.mystore.po.Notice;
import javafx.beans.property.adapter.JavaBeanProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    List<Notice> findByUserId(Integer userId);
}
