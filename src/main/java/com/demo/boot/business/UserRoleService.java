package com.demo.boot.business;

import com.demo.boot.entity.UserRole;
import com.demo.boot.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleService {

    @Resource
    UserRoleMapper userRoleMapper;

    /**
     * 写入用户角色关联关系
     * @param userRole
     * @return
     */
    public int insert(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }
}
