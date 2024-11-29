package com.miaoyu.naigos.api.BlueArchive.mapper;

import com.miaoyu.naigos.api.BlueArchive.entity.BARecreateClassifyBriefEntity;
import com.miaoyu.naigos.api.BlueArchive.model.BARecreateClassifyModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RecreateBAClassifyMapper {
    @Select("SELECT * FROM api_ba_recreate_classify")
    List<BARecreateClassifyBriefEntity> selectAllBAClassifyBrief();

    @Select("SELECT * FROM api_ba_recreate_classify WHERE classify_id = #{classify_id}")
    BARecreateClassifyModel selectEligibleBAClassify(@Param("classify_id") String classifyId);
}
