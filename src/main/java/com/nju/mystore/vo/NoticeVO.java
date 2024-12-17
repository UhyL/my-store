package com.nju.mystore.vo;


import com.nju.mystore.enums.NoticeTitleEnum;
import com.nju.mystore.po.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NoticeVO {

    private Integer noticeId;

    private Integer userId;

    private NoticeTitleEnum noticeTitle;

    private String noticeContent;

    private Date createTime;


    public Notice toPO() {
        Notice notice = new Notice();
        notice.setNoticeId(noticeId);
        notice.setUserId(userId);
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
        notice.setCreateTime(createTime);
        return notice;
    }
}
