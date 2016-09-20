package com.demo.boot.mapper;

import com.demo.boot.entity.RolePermission;

public interface RolePermissionMapper {
    int delete(Integer id);

    int insert(RolePermission record);

    RolePermission get(Integer id);

    int update(RolePermission record);
}