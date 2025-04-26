package com.miaoyu.naigos.blueArchive.service;

import com.miaoyu.naigos.blueArchive.mapper.BaWorkMapper;
import com.miaoyu.naigos.blueArchive.model.BaWorkModel;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaWorkService {
    @Autowired
    private BaWorkMapper baWorkMapper;

    /**根据school_id获取全部符合条件的work实体*/
    public Map<String, Object> getBaWorksBySchoolIdService(String schoolId) {
        return new SuccessMap().standardSuccessMap(baWorkMapper.selectListBySchoolId(schoolId));
    }

    /**根据club_id获取全部符合条件的work实体*/
    public Map<String, Object> getBaWorksByClubIdService(String clubId) {
        return new SuccessMap().standardSuccessMap(baWorkMapper.selectListByClubId(clubId));
    }
}
