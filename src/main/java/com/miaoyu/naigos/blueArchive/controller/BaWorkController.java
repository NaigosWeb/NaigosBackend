package com.miaoyu.naigos.blueArchive.controller;

import com.miaoyu.naigos.blueArchive.service.BaWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ba/work")
public class BaWorkController {
    @Autowired
    private BaWorkService baWorkService;

    /**
     * 获取所有work的一级分类
     * @return List类型中包含所有BA中work的一级分类的实体*/
    @GetMapping("/first_classifies")
    public Map<String, Object> getBaFirstClassifiesControl() {
        return null;
    }

    /**
     * 根据work的一级分类id获取所有符合的work
     * @param workFirstClassifyId work的一级分类id
     * @return List类型中包含所有BA中符合一级分类条件的work实体*/
    @GetMapping("/works_by_first_classify")
    public Map<String, Object> getBaWorksByFirstClassifyControl(
            @RequestParam("work_first_classify_id") String workFirstClassifyId
    ) {
        return null;
    }

    /**
     * 根据school获取所有符合的work
     * @param schoolId school的id
     * @return List类型中包含所有BA中符合school的work实体*/
    @GetMapping("/works_by_school")
    public Map<String, Object> getBaWorksBySchoolControl(
            @RequestParam("school_id") String schoolId
    ) {
        return baWorkService.getBaWorksBySchoolIdService(schoolId);
    }

    /**
     * 根据club获取所有符合的work
     * @param clubId club的id
     * @return List类型中包含所有BA中符合school的work实体*/
    @GetMapping("/works_by_club")
    public Map<String, Object> getBaWorksByClubControl(
            @RequestParam("club_id") String clubId
    ) {
        return baWorkService.getBaWorksByClubIdService(clubId);
    }

    /**根据自己的token或者传来的uuid获取符合条件的works
     * @param token 不必需 用户的网页token
     * @param uuid 不必需 用户uuid
     * @return List类型中包含所有BA中符合uuid的work实体*/
    @GetMapping("works_by_uuid")
    public Map<String, Object> getBaWorkByUuidControl(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestParam(value = "uuid", required = false) String uuid
    ) {
        return null;
    }

    /**
     * 根据student获取所有符合的work
     * @param studentId student的id
     * @return List类型中包含所有BA中符合student的work实体*/
    @GetMapping("/works_by_student")
    public Map<String, Object> getBaWorksByStudentControl(
            @RequestParam("student_id") String studentId
    ) {
        return null;
    }

    /**
     * 根据work_id获取唯一BA的work
     * @param workId work_id
     * @return 唯一检索的work*/
    @GetMapping("/only")
    public Map<String, Object> getBaWorkControl(
            @RequestParam("work_id") String workId
    ) {
        return null;
    }
}
