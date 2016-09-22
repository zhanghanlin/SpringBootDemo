package com.demo.boot.mapper;

import com.demo.boot.entity.RolePermission;

public interface RolePermissionMapper {

    int delete(String id);

    int insert(RolePermission record);

    RolePermission get(String id);

    int update(RolePermission record);
}