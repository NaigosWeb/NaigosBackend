package com.miaoyu.naigos.user.mapper;

import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserJudgeModel;

public interface GetUserArchiveMapper {
    UserArchiveModel getUserArchiveByEmail(String email);
    UserArchiveModel getUserArchiveByUuid(String uuid);
    UserArchiveModel getUserArchiveByUniqueId(Integer uniqueId);

    UserJudgeModel getUserJudgeByQqid(Integer qqId);
    boolean createUserJudgeFromScore(Integer qqId, String score);
    boolean updateUserJudgeFromScore(Integer qqId, String score);
}
