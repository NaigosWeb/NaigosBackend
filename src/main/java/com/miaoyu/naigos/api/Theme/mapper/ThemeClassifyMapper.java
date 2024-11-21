package com.miaoyu.naigos.api.Theme.mapper;

import com.miaoyu.naigos.api.Theme.entity.ThemeClassifyBriefEntity;
import com.miaoyu.naigos.model.ThemeClassifyModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface ThemeClassifyMapper {
    @Select("SELECT * FROM api_theme_classify")
    List<ThemeClassifyBriefEntity> getAllThemeClassifyBrief();

    @Select("SELECT * FROM api_theme_classify WHERE classify_id = #{classify_id}")
    ThemeClassifyModel getThemeEligibleClassify(@Param("classify_id") String classifyId);
}
