package com.demo.boot.business;

import com.demo.boot.entity.Role;
import com.demo.boot.entity.User;
import com.demo.boot.mapper.RoleMapper;
import com.demo.boot.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

    /**
     * 根据ID
     *
     * @param id
     * @return
     */
    public User get(Integer id) {
        User user = userMapper.get(id);
        List<Role> roles = roleMapper.getRoleByUser(user.getId());
        user.setRoles(roles);
        return user;
    }

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    public User getByUserName(String userName) {
        User user = userMapper.getByUserName(userName);
        List<Role> roles = roleMapper.getRoleByUser(user.getId());
        user.setRoles(roles);
        return user;
    }

    /**
     * 返回所有用户
     *
     * @return
     */
    public List<User> getAll() {
        return userMapper.getAll();
    }

    /**
     * 分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<User> getAllByPage(int pageNo, int pageSize, String orderBy) {
        Page<User> page = PageHelper.startPage(pageNo, pageSize, orderBy);
        userMapper.getAll();
        return page;
    }
}
