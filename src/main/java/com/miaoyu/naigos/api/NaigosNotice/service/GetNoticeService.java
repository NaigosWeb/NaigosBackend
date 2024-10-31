package com.miaoyu.naigos.api.NaigosNotice.service;

import com.miaoyu.naigos.api.NaigosNotice.mapper.NaigosNoticeMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.NaigosNoticeModel;
import com.miaoyu.naigos.model.UserPermiModel;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.userPermi.UserPermi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetNoticeService {
    @Autowired
    private NaigosNoticeMapper naigosNoticeMapper;

    public Map<String, Object> getAllNoticeService(){
        return new SuccessMap().standardSuccessMap(naigosNoticeMapper.getAllNotice());
    }
    public Map<String, Object> getNoticeByIdService(String noticeId){
        NaigosNoticeModel notice = naigosNoticeMapper.getNoticeById(noticeId);
        if(notice == null){
            return new ErrorMap().errorMap("该公告不存在！");
        }
        return new SuccessMap().standardSuccessMap(notice);
    }
    public Map<String, Object> getAllNoticeByUuidService(String uuid){
        return new SuccessMap().standardSuccessMap(naigosNoticeMapper.getAllNoticeByUuid(uuid));
    }
}
