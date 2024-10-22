package com.miaoyu.naigos.user.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserJudgeModel;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import com.miaoyu.naigos.user.mapper.UserCheckinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service
public class UserCheckinService {
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;
    @Autowired
    private UserCheckinMapper userCheckinMapper;

    public Map<String, Object> webCheckinService(String uuid) {
        UserArchiveModel userArchive = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        Integer qqId = userArchive.getQq_id();
        if (qqId == null) {
            return new ErrorMap().errorMap("账号还没有绑定您的QQ号无法启用全部功能！");
        }
        // 生成yyyyMMDD格式化日期
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        // 生成15-35之间随机数
        Random random = new Random();
        int randomInt = 15 + random.nextInt(35 - 15 + 1);
        // 处理是否本日首次签到
        UserJudgeModel userJudge = getUserArchiveMapper.getUserJudgeByQqid(qqId);
        if (userJudge == null) {
            // 库表中没有该用户的记录 创建记录 完成签到
            boolean createJudge = getUserArchiveMapper.createUserJudgeFromScore(qqId, formattedDate);
            if (createJudge) {
                Integer score = userArchive.getScore();
                score += randomInt;
                boolean isUpdateScore = userCheckinMapper.updateWebUserScore(uuid, score);
                if (isUpdateScore) {
                    return new NormalMap().normalSuccessMap("签到成功！获取" + randomInt + "个小布丁，账号共有" + score + "个小布丁");
                } else {
                    return new ErrorMap().errorMap("签到失败！ERROR_CREATE_1");
                }
            } else {
                return new ErrorMap().errorMap("签到失败！ERROR_CREATE_2");
            }
        }
        // 有表且当天首日签到
        if (!Objects.equals(userJudge.getScore(), formattedDate)) {
            boolean isUpdateScoreJudge = getUserArchiveMapper.updateUserJudgeFromScore(qqId, formattedDate);
            if (isUpdateScoreJudge) {
                Integer score = userArchive.getScore();
                score += randomInt;
                boolean isUpdateScore = userCheckinMapper.updateWebUserScore(uuid, score);
                if (isUpdateScore) {
                    return new NormalMap().normalSuccessMap("签到成功！获取" + randomInt + "个小布丁，账号共有" + score + "个小布丁");
                } else {
                    return new ErrorMap().errorMap("签到失败！ERROR_UPDATE_1");
                }
            } else {
                return new ErrorMap().errorMap("签到失败！ERROR_UPDATE_2");
            }
        } else {
            return new ErrorMap().errorMap("您今天已经完成过签到！");
        }
    }
}
