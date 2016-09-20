package com.demo.boot.business;

import com.demo.boot.entity.User;
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

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    public User getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }

    /**
     * 返回所有用户
     *
     * @return
     */
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    /**
     * 分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<User> getAllUserByPage(int pageNo, int pageSize, String orderBy) {
        Page<User> page = PageHelper.startPage(pageNo, pageSize, orderBy);
        userMapper.getAllUser();
        return page;
    }
}
