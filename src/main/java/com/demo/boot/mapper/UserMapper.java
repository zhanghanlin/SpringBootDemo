package com.demo.boot.mapper;

import com.demo.boot.entity.User;

import java.util.List;

public interface UserMapper {

    int delete(String id);

    int insert(User record);

    User get(String id);

    User getByUserName(String userName);

    List<User> getAll();

    int update(User record);

    int insertUserRole(User record);

    int deleteUserRole(String id);

    List<User> getByRole(String id);

    List<User> getByNotRole(String id);
}