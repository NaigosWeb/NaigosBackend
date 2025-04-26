package com.miaoyu.naigos.blueArchive.mapper;

import com.miaoyu.naigos.blueArchive.model.BaStudentModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BaStudentMapper {
    @Select("SELECT * FROM ba_student")
    List<BaStudentModel> selectAll();
    @Select("SELECT * FROM ba_student WHERE school = #{school_id}")
    List<BaStudentModel> selectListBySchoolId(@Param("school_id") String school_id);
    @Select("SELECT * FROM ba_student WHERE club = #{club_id}")
    List<BaStudentModel> selectListByClubId(@Param("club_id") String club_id);
    @Select("SELECT * FROM ba_student WHERE id = #{student_id}")
    BaStudentModel selectById(@Param("student_id") String id);
    @Insert("INSERT INTO ba_student (id, cn_name, jp_name, kr_name, en_name, introduce, avatar_square, avatar_rectangle, body_image, school, club) VALUES (#{id}, #{cn_name}, #{jp_name}, #{kr_name}, #{en_name}, #{introduce}, #{avatar_square}, #{avatar_rectangle}, #{body_image}, #{school}, #{club})")
    boolean insert(BaStudentModel baStudentModel);
    boolean update(BaStudentModel baStudentModel);
    @Delete("DELETE FROM ba_student WHERE id = #{student_id}")
    boolean delete(@Param("student_id") String id);
}
