package com.nju.mystore.repository;

import com.nju.mystore.po.Notice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice, Integer> {
    List<Notice> findByUserId(Integer userId);
}
