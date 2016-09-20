package com.demo.boot.mapper;

import com.demo.boot.entity.UserRole;

public interface UserRoleMapper {
    int delete(Integer id);

    int insert(UserRole record);

    UserRole get(Integer id);

    int update(UserRole record);
}