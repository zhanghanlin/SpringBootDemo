package com.demo.boot.mapper;

import com.demo.boot.entity.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}