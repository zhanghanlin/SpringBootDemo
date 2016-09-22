package com.demo.boot.business;

import com.demo.boot.entity.Role;
import com.demo.boot.mapper.RoleMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    RoleMapper roleMapper;

    /**
     * 查询所有角色
     *
     * @return
     */
    public Page<Role> getAllByPage(int pageNo, int pageSize, String orderBy) {
        Page<Role> page = PageHelper.startPage(pageNo, pageSize, orderBy);
        roleMapper.getAll();
        return page;
    }

    /**
     * 根据用户Id查询角色
     *
     * @param userId
     * @return
     */
    public List<Role> getRoleByUser(String userId) {
        return roleMapper.getRoleByUser(userId);
    }

    public List<Role> getAll() {
        return roleMapper.getAll();
    }
}
