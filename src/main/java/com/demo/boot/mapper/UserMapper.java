package com.demo.boot.mapper;

import com.demo.boot.entity.User;

import java.util.List;

public interface UserMapper {
    int delete(Integer id);

    int insert(User record);

    User get(Integer id);

    User getByName(String userName);

    List<User> getUserList();

    int update(User record);
}