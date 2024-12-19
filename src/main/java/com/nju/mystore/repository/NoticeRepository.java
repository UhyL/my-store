package com.nju.mystore.repository;

import com.nju.mystore.enums.NoticeStatusEnum;
import com.nju.mystore.po.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    List<Notice> findByUserId(Integer userId);

    List<Notice> findByNoticeStatusAndUserId(NoticeStatusEnum noticeStatus, Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE Notice n SET n.noticeStatus = :noticeStatus WHERE n.noticeId = :noticeId")
    void updateNoticeStatusByNoticeId(@Param("noticeId") Integer noticeId, @Param("noticeStatus") NoticeStatusEnum noticeStatus);

}
