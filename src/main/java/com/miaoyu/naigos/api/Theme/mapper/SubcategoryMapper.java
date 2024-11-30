package com.miaoyu.naigos.api.Theme.mapper;

import com.miaoyu.naigos.model.ThemeIdSubcategoryModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubcategoryMapper {

    @Select("SELECT * FROM theme_id_subcategory WHERE subcategory = #{subcategory}")
    ThemeIdSubcategoryModel selectSubcategory(@Param("subcategory") String subcategory);

    @Select("SELECT * FROM theme_id_subcategory")
    List<ThemeIdSubcategoryModel> selectAllSubcategory();
}
