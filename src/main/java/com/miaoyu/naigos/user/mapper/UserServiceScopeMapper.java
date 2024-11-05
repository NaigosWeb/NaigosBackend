package com.miaoyu.naigos.user.mapper;

import com.miaoyu.naigos.model.UserServiceScopeModel;
import com.miaoyu.naigos.user.entity.UserServiceScopeEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserServiceScopeMapper {
    @Select("SELECT * FROM users_service_scope WHERE uuid = #{uuid} ")
    UserServiceScopeModel getServiceScopeByUuid(@Param("uuid") String uuid);

    @Select("SELECT * FROM service_scope WHERE service_name = #{service_name}")
    UserServiceScopeEntity getOnlyServiceScopeByServiceName(@Param("service_name") String serviceName);
}
