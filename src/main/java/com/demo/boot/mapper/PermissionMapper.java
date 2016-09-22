package com.demo.boot.mapper;

import com.demo.boot.entity.Permission;

import java.util.List;

public interface PermissionMapper {

    int delete(String id);

    int insert(Permission record);

    Permission get(String id);

    List<Permission> getPermissionByUser(String id);

    List<Permission> getAll();

    int update(Permission record);
}