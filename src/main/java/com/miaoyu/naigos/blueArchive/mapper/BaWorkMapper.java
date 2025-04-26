package com.miaoyu.naigos.blueArchive.mapper;

import com.miaoyu.naigos.blueArchive.model.BaWorkModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BaWorkMapper {
    List<BaWorkModel> selectAll();
    List<BaWorkModel> selectListByFirstClassify(@Param("first_classify_id") String first_classify_id);
    @Select("SELECT bw.* FROM ba_work bw JOIN ba_student bst ON bw.student = bst.id JOIN ba_school bs ON bs.id = #{school_id}")
    List<BaWorkModel> selectListBySchoolId(@Param("school_id") String school_id);
    @Select("SELECT bw.* FROM ba_work bw JOIN ba_student bst ON bw.student = bst.id JOIN ba_school_club bsc ON bsc.id = #{club_id}")
    List<BaWorkModel> selectListByClubId(@Param("club_id") String club_id);
    List<BaWorkModel> selectListByStudentId(@Param("student_id") String student_id);
    List<BaWorkModel> selectListByAuthor(@Param("uuid") String uuid);
    BaWorkModel selectById(@Param("id") String id);
    int insert(BaWorkModel baWorkModel);
    int update(BaWorkModel baWorkModel);
    int delete(@Param("id") String id);
}
