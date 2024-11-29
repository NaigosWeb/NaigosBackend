package com.miaoyu.naigos.api.BlueArchive.mapper;

import com.miaoyu.naigos.model.ThemeIdSubcategoryModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BASubcategoryMapper {

    @Select("SELECT * FROM theme_id_subcategory WHERE subcategory = #{subcategory}")
    ThemeIdSubcategoryModel selectSubcategory(@Param("subcategory") String subcategory);
}
