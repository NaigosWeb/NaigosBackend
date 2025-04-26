package com.miaoyu.naigos.blueArchive.mapper;

import com.miaoyu.naigos.blueArchive.model.BaSchoolModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BaSchoolMapper {
    @Select("SELECT * FROM ba_school")
    List<BaSchoolModel> selectAll();
    @Select("SELECT * FROM ba_school WHERE id = #{school_id}")
    BaSchoolModel selectById(@Param("school_id") String id);
    @Insert("INSERT INTO ba_school (id, cn_name, jp_name, kr_name, en_name, introduce, logo, beautify_logo, bg) VALUES (#{id}, #{cn_name}, #{jp_name}, #{kr_name}, #{en_name}, #{introduce}, #{logo}, #{beautify_logo}, #{bg})")
    boolean insert(BaSchoolModel baSchoolModel);
    @Update("UPDATE ba_school SET cn_name = #{cn_name}, jp_name = #{jp_name}, kr_name = #{kr_name}, en_name = #{en_name}, introduce = #{introduce}, logo = #{logo}, beautify_logo = #{beautify_logo}, bg = #{bg} WHERE id = #{id}")
    boolean update(BaSchoolModel baSchoolModel);
    @Delete("DELETE FROM ba_school WHERE id = #{school_id}")
    boolean delete(@Param("school_id") String id);
}
