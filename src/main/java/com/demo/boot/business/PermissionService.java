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
     * 查询用户的权限列表
     *
     * @param userId
     * @return
     */
    public List<Permission> getPermissionByUser(Integer userId) {
        return permissionMapper.getPermissionByUser(userId);
    }

    /**
     * 获取所有权限
     * @return
     */
    public List<Permission> getAll() {
        return permissionMapper.getAll();
    }
}
