package com.miaoyu.naigos.api.Theme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaoyu.naigos.api.Theme.entity.TestEntity;
import com.miaoyu.naigos.api.Theme.mapper.TestMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class TestService {
    @Autowired private TestMapper testMapper;

    public Map<String, Object> addTheme(String name, List<String> tagsArray, String themeId) throws JsonProcessingException {
        System.out.println(tagsArray.toString());
        TestEntity testEntity = new TestEntity();
        testEntity.setName(name);
        testEntity.setTags(new ObjectMapper().writeValueAsString(tagsArray));
        testEntity.setId(themeId);
        boolean result = testMapper.insertTheme(testEntity);
        if (result) {
            return new SuccessMap().standardSuccessMap("");
        }
        return new ErrorMap().errorMap("");
    }

    public Map<String, Object> getTheme(String name) {
        TestEntity testEntity = testMapper.selectTheme(name);
        if (testEntity != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String[] sa = objectMapper.readValue(testEntity.getTagsJson(), new TypeReference<String[]>() {});
                return new SuccessMap().standardSuccessMap(sa);
            } catch (IOException e) {
                e.printStackTrace();
                return new ErrorMap().errorMap("");
            }
        }
        return new ErrorMap().errorMap("");
    }
}
