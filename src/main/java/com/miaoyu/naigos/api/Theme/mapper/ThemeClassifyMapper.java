package com.miaoyu.naigos.api.Theme.mapper;

import com.miaoyu.naigos.api.Theme.entity.ThemeClassifyBriefEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ThemeClassifyMapper {
    @Select("SELECT * FROM api_theme_classify")
    List<ThemeClassifyBriefEntity> getAllThemeClassifyBrief();
}
