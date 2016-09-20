package com.demo.boot.controller;

import com.demo.boot.business.UserService;
import com.demo.boot.entity.User;
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
    UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView user() {
        List<User> list = userService.getAll();
        return new ModelAndView("boot/user", "userList", list);
    }
}
