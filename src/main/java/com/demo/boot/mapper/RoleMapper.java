package com.demo.boot.mapper;

import com.demo.boot.entity.Role;

import java.util.List;

public interface RoleMapper {
    int delete(Integer id);

    int insert(Role record);

    Role get(Integer id);

    List<Role> getRoleByUser(Integer id);

    List<Role> getAll();

    int update(Role record);
}