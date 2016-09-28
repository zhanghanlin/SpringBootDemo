package com.demo.boot.mapper;

import com.demo.boot.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    int delete(String id);

    int insert(Role record);

    Role get(String id);

    List<Role> getByUser(String id);

    List<Role> getAll();

    int update(Role record);

    int deleteRolePermission(String id);

    int insertRolePermission(Role record);

    int outUserInRole(@Param("roleId") String roleId, @Param("userId") String userId);
}