package com.demo.boot.mapper;

import com.demo.boot.entity.Permission;

import java.util.List;

public interface PermissionMapper {
    int delete(Integer id);

    int insert(Permission record);

    Permission get(Integer id);

    List<Permission> getPermissionByUser(Integer id);

    int update(Permission record);
}