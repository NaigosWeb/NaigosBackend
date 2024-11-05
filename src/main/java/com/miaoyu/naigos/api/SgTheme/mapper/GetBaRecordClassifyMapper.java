package com.miaoyu.naigos.api.SgTheme.mapper;

import com.miaoyu.naigos.api.SgTheme.entity.BaRecordClassifyBriefEntity;
import com.miaoyu.naigos.api.SgTheme.model.BaRecordClassifyModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GetBaRecordClassifyMapper {
    @Select("SELECT * FROM api_ba_record_classify")
    List<BaRecordClassifyBriefEntity> getAllBriefBaRecordClassify();

    @Select("SELECT * FROM api_ba_record_classify WHERE classify_id = #{classify_id}")
    BaRecordClassifyModel getOnlyBaRecordClassify(@Param("classify_id") String classifyId);
}
