package com.miaoyu.naigos.blueArchive.controller;

import com.miaoyu.naigos.blueArchive.model.BaStudentModel;
import com.miaoyu.naigos.blueArchive.service.BaStudentService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ba/student")
public class BaStudentController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private BaStudentService baStudentService;

    /**获取全部BA中的student实体
     * @return List类型中包含全部的student实体*/
    @GetMapping("/all")
    public Map<String, Object> getBaAllStudentControl() {
        return baStudentService.getBaAllStudentsService();
    }

    /**根据school_id获取符合条件的所有student实体
     * @param schoolId school的id
     * @return List类型中包含所有符合条件的student实体*/
    @GetMapping("/student_by_school")
    public Map<String, Object> getBaStudentBySchoolControl(@RequestParam("school_id") String schoolId) {
        return baStudentService.getBaStudentsBySchoolIdService(schoolId);
    }

    /**根据club_id获取符合条件的所有student实体
     * @param clubId club的id
     * @return List类型中包含所有符合条件的student实体*/
    @GetMapping("/students_by_club")
    public Map<String, Object> getBaStudentsByClubControl(@RequestParam("club_id") String clubId) {
        return baStudentService.getBaStudentsByClubIdService(clubId);
    }

    /**根据student_id获取唯一符合的student实体
     * @param studentId student的id
     * @return 唯一的student实体*/
    @GetMapping("/only")
    public Map<String, Object> getBaOnlyStudentControl(
            @RequestParam("student_id") String studentId
    ) {
        return baStudentService.getBaStudentByIdService(studentId);
    }

    /**上传student实体
     * @param token 管理员以上权限的token
     * @param upType 上传的方式"upload"/"update"
     * @param request student实体
     * @return 上传是否成功*/
    @PostMapping("/up_student")
    public Map<String, Object> upBaStudentControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("up_type") String upType,
            @RequestBody BaStudentModel request
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1) {
            return payload;
        }
        return baStudentService.upBaStudentService(payload.get("data").toString(), upType, request);
    }

    /**删除student实体
     * @param token 管理员以上权限的token
     * @param studentId student的id
     * @return 删除是否成功*/
    @DeleteMapping("/delete_student")
    public Map<String, Object> deleteBaStudentControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("student_id") String studentId
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1) {
            return payload;
        }
        return baStudentService.deleteBaStudentService(payload.get("data").toString(), studentId);
    }
}
