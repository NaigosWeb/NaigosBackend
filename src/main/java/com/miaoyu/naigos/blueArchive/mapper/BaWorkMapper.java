package com.miaoyu.naigos.blueArchive.mapper;

import com.miaoyu.naigos.blueArchive.model.BaWorkModel;
import com.miaoyu.naigos.blueArchive.model.entity.BaWorkEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BaWorkMapper {
    @Select("SELECT * FROM ba_work")
    List<BaWorkEntity> selectAll();
    @Select("SELECT * FROM ba_work WHERE first_classify = #{first_classify_id}")
    List<BaWorkEntity> selectListByFirstClassify(@Param("first_classify_id") String first_classify_id);
    @Select("SELECT bw.* FROM ba_work bw JOIN ba_student bst ON bw.student = bst.id JOIN ba_school bs ON bst.school = bs.id WHERE bs.id = #{school_id}")
    List<BaWorkEntity> selectListBySchoolId(@Param("school_id") String school_id);
    @Select("SELECT bw.* FROM ba_work bw JOIN ba_student bst ON bw.student = bst.id JOIN ba_school_club bsc ON bst.club = bsc.id WHERE bsc.id = #{club_id}")
    List<BaWorkEntity> selectListByClubId(@Param("club_id") String club_id);
    @Select("SELECT * FROM ba_work WHERE student = #{student_id}")
    List<BaWorkEntity> selectListByStudentId(@Param("student_id") String student_id);
    @Select("SELECT * FROM ba_work WHERE author = #{uuid}")
    List<BaWorkEntity> selectListByAuthor(@Param("uuid") String uuid);
    @Select("SELECT * FROM ba_work WHERE id = #{id}")
    BaWorkModel selectById(@Param("id") String id);
    @Insert("INSERT INTO ba_work (id, name, content, author, link, first_classify, student, cover_image, last_time) VALUES (#{id}, #{name}, #{content}, #{author}, #{link}, #{first_classify}, #{student}, #{cover_image}, #{last_time})")
    int insert(BaWorkModel baWorkModel);
    @Update("UPDATE ba_work SET name = #{name}, content = #{content}, author = #{author}, link = #{link}, first_classify = #{first_classify}, student = #{student}, cover_image = #{cover_image}, last_time = #{last_time} WHERE id = #{id}")
    int update(BaWorkModel baWorkModel);
    @Delete("DELETE FROM ba_work WHERE id = #{id}")
    int delete(@Param("id") String id);
}
