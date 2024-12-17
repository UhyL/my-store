package com.nju.mystore.po;

import com.nju.mystore.enums.NoticeTitleEnum;
import com.nju.mystore.vo.NoticeVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notice {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer noticeId;

    private Integer userId;

    private NoticeTitleEnum noticeTitle;

    private String noticeContent;

    private Date createTime;

    public NoticeVO toVO() {
        NoticeVO vo = new NoticeVO();
        vo.setNoticeId(this.noticeId);
        vo.setUserId(this.userId);
        vo.setNoticeTitle(this.noticeTitle);
        vo.setNoticeContent(this.noticeContent);
        vo.setCreateTime(this.createTime);
        return vo;
    }
}
