package com.demo.boot.mapper;

import com.demo.boot.entity.Role;

import java.util.List;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    List<Role> getRolesByUser(Integer id);
}