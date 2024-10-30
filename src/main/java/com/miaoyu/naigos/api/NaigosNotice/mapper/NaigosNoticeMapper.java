package com.miaoyu.naigos.api.NaigosNotice.mapper;

import com.miaoyu.naigos.model.NaigosNoticeModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NaigosNoticeMapper {
    List<NaigosNoticeModel> getAllNotice();
    NaigosNoticeModel getNoticeById(@Param("notice_id") String noticeId);
    boolean uploadNotice(@Param("noticeId") String noticeId,
                         @Param("title") String title,
                         @Param("content") String content,
                         @Param("author") String uuid,
                         @Param("attachment") String attachment,
                         @Param("image") String image,
                         @Param("upload_date") long upload_date,
                         @Param("last_date") long last_date);
    boolean updateNotice(
            @Param("notice_id") String noticeId,
            @Param("title") String title,
            @Param("content") String content,
            @Param("attachment") String attachment,
            @Param("image") String image,
            @Param("last_date") long lastDate);
    boolean deleteNotice(@Param("notice_id") String noticeId, @Param("author") String uuid);
}
