package com.miaoyu.naigos.user.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.NoLoginMap;
import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.user.mapper.EditArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class EditArchiveService {
    @Autowired
    private GetUserArchiveService getUserArchiveService;
    @Autowired
    private EditArchiveMapper editArchiveMapper;

    public Map<String, Object> editArchiveService(String uuid, UserArchiveModel request) {
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(2, uuid);
        if (userArchive == null) {
            return new ErrorMap().errorMap("未找到用户档案！");
        }
        if (request.getNickname() == null){
            request.setNickname(userArchive.getNickname());
        } else {
            UserArchiveModel userArchiveByEmail = getUserArchiveService.getUserArchive(1, request.getEmail());
            if (userArchiveByEmail != null &&
                    !Objects.equals(userArchive.getGroup_real_user_id(), userArchiveByEmail.getGroup_real_user_id())
            ) {
                return new ErrorMap().errorMap("您要更换的电子邮箱已经绑定其他档案！");
            }
        }
        if (request.getEmail() == null){
            request.setEmail(userArchive.getEmail());
        }
        if (request.getQq_id() == null){
            request.setQq_id(userArchive.getQq_id());
        } else {
            UserArchiveModel userArchiveByQqId = getUserArchiveService.getUserArchive(3, request.getQq_id().toString());
            if (userArchiveByQqId != null &&
                    !Objects.equals(userArchive.getGroup_real_user_id(), userArchiveByQqId.getGroup_real_user_id())
            ) {
                return new ErrorMap().errorMap("您要更换的QQ号已经绑定其他档案！");
            }
        }
        if (request.getCity() == null){
            request.setCity(userArchive.getCity());
        }
        boolean b = editArchiveMapper.editArchiveByUuid(uuid, request.getNickname(), request.getEmail(), request.getQq_id(), request.getCity());
        if (b){
            return new NormalMap().normalSuccessMap("修改成功！");
        }
        return new ErrorMap().errorMap("修改失败！");
    }
}
