package com.demo.boot.business;

import com.demo.boot.entity.Role;
import com.demo.boot.mapper.RoleMapper;
import com.demo.boot.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    RoleMapper roleMapper;

    public Role get(String id) {
        return roleMapper.get(id);
    }

    public void insert(Role role) {
        try {
            role.preInsert();
            roleMapper.insert(role);
            if (StringUtils.isBlank(role.getId())) {
                throw new RuntimeException("角色创建失败");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void update(Role role) {
        try {
            role.preUpdate();
            roleMapper.update(role);
        } catch (Exception e) {
            throw new RuntimeException("角色更新失败");
        }
    }

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
    public List<Role> getByUser(String userId) {
        return roleMapper.getByUser(userId);
    }

    public List<Role> getAll() {
        return roleMapper.getAll();
    }

    /**
     * 根据角色Id删除权限关联
     * @param roleId
     */
    public void deleteRolePermission(String roleId){
        roleMapper.deleteRolePermission(roleId);
    }

    public void insertRolePermission(Role role){
        roleMapper.insertRolePermission(role);
    }
}
