package com.demo.boot.mapper;

import com.demo.boot.entity.UserRole;

public interface UserRoleMapper {

    int delete(String id);

    int insert(UserRole record);

    UserRole get(String id);

    int update(UserRole record);
}