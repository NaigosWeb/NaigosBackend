package com.miaoyu.naigos.blueArchive.mapper;

import com.miaoyu.naigos.blueArchive.model.BaFirstClassifyModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BaFirstClassifyMapper {
    @Select("SELECT * FROM ba_first_classify")
    List<BaFirstClassifyModel> selectAll();
}
