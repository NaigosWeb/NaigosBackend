package com.miaoyu.naigos.blueArchive.mapper;

import com.miaoyu.naigos.blueArchive.model.BaSchoolClubModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    boolean update(BaSchoolClubModel baSchoolClubModel);
    boolean delete(@Param("club_id") String club_id);
}
