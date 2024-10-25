package com.miaoyu.naigos.user.mapper;

import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserJudgeModel;

public interface GetUserArchiveMapper {
    UserArchiveModel getUserArchiveByEmail(String email);
    UserArchiveModel getUserArchiveByUuid(String uuid);
    UserArchiveModel getUserArchiveByUniqueId(Integer uniqueId);
    UserArchiveModel getUserArchiveByQqId(Integer qqId);
    boolean updateUserArchiveByUuid(Integer qqId,
                                    String nickname,
                                    String city,
                                    Integer score,
                                    Integer favorite,
                                    String avatar,
                                    String uuid);
    boolean webRegisterUserArchiveByEmail(String email,
                                          String nickname,
                                          String uuid,
                                          Integer qqId);


    UserJudgeModel getUserJudgeByQqid(Integer qqId);
    boolean createUserJudgeInit(Integer qqId);
    boolean createUserJudgeInitNoTrans(Integer qqId);
    boolean createUserJudgeFromScore(Integer qqId, String score);
    boolean updateUserJudgeFromScore(Integer qqId, String score);
    boolean updateUserJudgeTransFalse(Integer qqId);
}
