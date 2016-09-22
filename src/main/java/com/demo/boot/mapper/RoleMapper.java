package com.demo.boot.mapper;

import com.demo.boot.entity.Role;

import java.util.List;

public interface RoleMapper {

    int delete(String id);

    int insert(Role record);

    Role get(String id);

    List<Role> getRoleByUser(String id);

    List<Role> getAll();

    int update(Role record);
}