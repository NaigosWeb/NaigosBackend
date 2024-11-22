package com.miaoyu.naigos.api.Theme.mapper;

import com.miaoyu.naigos.api.Theme.entity.TestEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface TestMapper {
    @Insert("INSERT INTO test (name, tags, id) VALUES (#{name}, #{tagsJson}, #{id})")
    boolean insertTheme(TestEntity testEntity);

    @Select("SELECT * FROM test WHERE name = #{name}")
//    @Results({
//            @Result(property = "tagsJson", column = "tags")
//    })
    TestEntity selectTheme(String name);
}
