package com.demo.boot.business;

import com.demo.boot.entity.Role;
import com.demo.boot.entity.User;
import com.demo.boot.mapper.RoleMapper;
import com.demo.boot.mapper.UserMapper;
import com.demo.boot.utils.StringUtils;
import com.demo.boot.utils.UserUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    public void insert(User user) {
        try {
            user.preInsert();
            userMapper.insert(user);
            if (StringUtils.isBlank(user.getId())) {
                throw new RuntimeException("用户创建失败");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 更新用户
     *
     * @param user
     */
    public void update(User user) {
        try {
            user.preUpdate();
            userMapper.update(user);
            UserUtils.clearCache(user);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 根据ID
     *
     * @param id
     * @return
     */
    public User get(String id) {
        User user = userMapper.get(id);
        List<Role> roles = roleMapper.getByUser(user.getId());
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
        List<Role> roles = roleMapper.getByUser(user.getId());
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

    /**
     * 写入用户角色关系
     *
     * @param user
     */
    public void insertUserRole(User user) {
        userMapper.insertUserRole(user);
    }

    /**
     * 查询该角色下的用户
     *
     * @param id
     * @return
     */
    public List<User> getByRole(String id) {
        return userMapper.getByRole(id);
    }

    /**
     * 查询不包含该角色的用户
     *
     * @param id
     * @return
     */
    public List<User> getByNotRole(String id) {
        return userMapper.getByNotRole(id);
    }
}
