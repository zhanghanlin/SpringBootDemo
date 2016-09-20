package com.demo.boot.controller;

import com.demo.boot.entity.User;
import com.demo.boot.mapper.UserMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@EnableAutoConfiguration
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView user() {
        List<User> list = userMapper.getUserList();
        return new ModelAndView("user", "userList", list);
    }
}
