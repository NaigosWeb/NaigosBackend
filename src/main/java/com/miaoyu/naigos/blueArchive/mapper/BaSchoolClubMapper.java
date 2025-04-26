package com.miaoyu.naigos.blueArchive.mapper;

import com.miaoyu.naigos.blueArchive.model.BaSchoolClubModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BaSchoolClubMapper {
    @Select("SELECT * FROM ba_school_club")
    List<BaSchoolClubModel> selectAll();
    @Select("SELECT * FROM ba_school_club WHERE id = #{club_id}")
    BaSchoolClubModel selectById(@Param("club_id") String id);
    @Select("SELECT * FROM ba_school_club WHERE school = #{school_id}")
    List<BaSchoolClubModel> selectListBySchoolId(@Param("school_id") String school_id);
    @Insert("INSERT INTO ba_school_club (id, school, cn_name, jp_name, kr_name, en_name, logo, bg) VALUES (#{id},#{school}, #{cn_name}, #{jp_name}, #{kr_name}, #{en_name}, #{logo}, #{bg})")
    boolean insert(BaSchoolClubModel baSchoolClubModel);
    @Update("UPDATE ba_school_club SET school = #{school}, cn_name = #{cn_name}, jp_name = #{jp_name}, kr_name = #{kr_name}, en_name = #{en_name}, logo = #{logo}, bg = #{bg} WHERE id = #{id}")
    boolean update(BaSchoolClubModel baSchoolClubModel);
    @Delete("DELETE FROM ba_school_club WHERE id = #{club_id}")
    boolean delete(@Param("club_id") String club_id);
}
