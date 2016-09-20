package com.demo.boot.business;

import com.demo.boot.entity.Role;
import com.demo.boot.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    RoleMapper roleMapper;

    /**
     * 查询用户的角色列表
     *
     * @param userId
     * @return
     */
    public List<Role> getRoleByUser(Integer userId) {
        return roleMapper.getRoleByUser(userId);
    }
}
