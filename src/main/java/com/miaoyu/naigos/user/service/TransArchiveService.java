package com.miaoyu.naigos.user.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserJudgeModel;
import com.miaoyu.naigos.user.entity.OldArchiveEntity;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import com.miaoyu.naigos.user.mapper.TransArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransArchiveService {
    @Autowired
    private GetUserArchiveService getUserArchiveService;
    @Autowired
    private TransArchiveMapper transArchiveMapper;
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;

    public Map<String, Object> transArchiveService(String uuid) {
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(2, uuid);
        if (userArchive == null) {
            return new ErrorMap().errorMap("未找到用户档案");
        }
        Integer qqId = userArchive.getQq_id();
        if (qqId == null) {
            return new ErrorMap().errorMap("未绑定QQ号，请为当前档案绑定QQ号！");
        }
        OldArchiveEntity oldArchive = transArchiveMapper.getOldArchiveByQqId(String.valueOf(qqId));
        UserJudgeModel userJudge = getUserArchiveMapper.getUserJudgeByQqid(qqId);
        if (oldArchive == null) {
            return new ErrorMap().errorMap("旧档案不存在！");
        }
        if (userJudge != null && userJudge.isTransfer_archive()) {
            return new ErrorMap().errorMap("您曾完成过档案转移！不可再执行！");
        }
        if (userJudge == null) {
            boolean userJudgeInitNoTrans = getUserArchiveMapper.createUserJudgeInitNoTrans(qqId);
            if (!userJudgeInitNoTrans) {
                return new ErrorMap().errorMap("服务器数据错误！");
            }
        }
        boolean b = getUserArchiveMapper.updateUserJudgeTransFalse(qqId);
        if (!b) {
            return new ErrorMap().errorMap("服务器数据错误！");
        }
        boolean isUpdateArchive = getUserArchiveMapper.updateUserArchiveByUuid(
                qqId,
                oldArchive.getUsername(),
                oldArchive.getCity(),
                userArchive.getScore() + oldArchive.getScore(),
                userArchive.getFavorite() + oldArchive.getFavorite(),
                oldArchive.getAvatar(),
                uuid);
        if (isUpdateArchive) {
            return new NormalMap().normalSuccessMap("档案转移成功！");
        } else {
            return new ErrorMap().errorMap("找到旧档案！转移失败！请联系站长！");
        }
    }
}
