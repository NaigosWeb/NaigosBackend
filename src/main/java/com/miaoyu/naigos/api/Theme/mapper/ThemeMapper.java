package com.miaoyu.naigos.api.Theme.mapper;

import com.miaoyu.naigos.api.Theme.entity.ThemeBriefEntity;
import com.miaoyu.naigos.model.ThemeModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ThemeMapper {
    @Select("SELECT * FROM api_theme")
    List<ThemeBriefEntity> selectAllThemeBrief();

    @Select("SELECT * FROM api_theme WHERE classify_id = #{classify_id}")
    List<ThemeBriefEntity> selectAllEligibleThemeBrief(@Param("classify_id") String classifyId);

    @Select("SELECT * FROM api_theme WHERE classify_id IS NULL")
    List<ThemeBriefEntity> selectAllNullThemeBrief();

    @Select("SELECT * FROM api_theme WHERE theme_id = #{theme_id}")
    ThemeModel selectThemeById(@Param("theme_id") String themeId);

    @Insert("INSERT INTO api_theme (name, url, introduce, header_image, details_image, cost, eject_image, theme_id, classify_id, author) VALUES (#{name}, #{url}, #{introduce}, #{header_image}, #{details_image}, #{cost}, #{eject_image}, #{theme_id}, #{classify_id}, #{author})")
    boolean insertTheme(ThemeModel themeModel);
}
