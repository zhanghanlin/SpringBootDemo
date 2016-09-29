package com.demo.boot.business;

import com.demo.boot.entity.Permission;
import com.demo.boot.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService {

    @Resource
    PermissionMapper permissionMapper;

    /**
     * 根据角色查询权限
     *
     * @param roleId
     * @return
     */
    public List<Permission> getByRole(String roleId) {
        return permissionMapper.getByRole(roleId);
    }

    /**
     * 查询用户的权限列表
     *
     * @param userId
     * @return
     */
    public List<Permission> getByUser(String userId) {
        return permissionMapper.getByUser(userId);
    }

    /**
     * 获取所有权限
     *
     * @return
     */
    public List<Permission> getAll() {
        return permissionMapper.getAll();
    }

    public Permission get(String id) {
        return permissionMapper.get(id);
    }

    /**
     * 更新权限
     *
     * @param permission
     */
    public void update(Permission permission) {
        permission.preUpdate();
        permissionMapper.update(permission);
    }
}
