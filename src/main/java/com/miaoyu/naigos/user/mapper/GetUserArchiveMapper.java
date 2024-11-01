package com.miaoyu.naigos.user.mapper;

import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserJudgeModel;
import com.miaoyu.naigos.model.UserPermiModel;

public interface GetUserArchiveMapper {
    UserArchiveModel getUserArchiveByEmail(String email);
    UserArchiveModel getUserArchiveByUuid(String uuid);
    UserArchiveModel getUserArchiveByUniqueId(Integer uniqueId);
    UserArchiveModel getUserArchiveByQqId(Long qqId);
    UserPermiModel getUserPermiByUuid(String uuid);
    boolean updateUserArchiveByUuid(Long qqId,
                                    String nickname,
                                    String city,
                                    Integer score,
                                    Integer favorite,
                                    String avatar,
                                    String uuid);
    boolean webRegisterUserArchiveByEmail(String email,
                                          String nickname,
                                          String uuid,
                                          Long qqId);


    UserJudgeModel getUserJudgeByQqid(String qqId);
    boolean createUserJudgeInit(String qqId);
    boolean createUserJudgeInitNoTrans(String qqId);
    boolean createUserJudgeFromScore(String qqId, String score);
    boolean updateUserJudgeFromScore(String qqId, String score);
    boolean updateUserJudgeTransFalse(String qqId);
}
