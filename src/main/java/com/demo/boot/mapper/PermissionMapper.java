package com.demo.boot.mapper;

import com.demo.boot.entity.Permission;

public interface PermissionMapper {
    int delete(Integer id);

    int insert(Permission record);

    Permission get(Integer id);

    int update(Permission record);
}