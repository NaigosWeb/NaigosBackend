package com.miaoyu.naigos.blueArchive.mapper;

import com.miaoyu.naigos.blueArchive.model.BaStudentModel;
import org.apache.ibatis.annotations.*;

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
    @Update("UPDATE ba_student SET cn_name = #{cn_name}, jp_name = #{jp_name}, kr_name = #{kr_name}, introduce = #{introduce}, avatar_square = #{avatar_square}, avatar_rectangle = #{avatar_rectangle}, body_image = #{body_image}, school = #{school}, club = #{club} WHERE id = #{id}")
    boolean update(BaStudentModel baStudentModel);
    @Delete("DELETE FROM ba_student WHERE id = #{student_id}")
    boolean delete(@Param("student_id") String id);
}
