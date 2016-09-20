package com.demo.boot.controller.api;

import com.demo.boot.business.UserService;
import com.demo.boot.entity.User;
import com.demo.boot.vo.TablePage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@EnableAutoConfiguration
@RestController
@RequestMapping("user/api")
public class UserApiController {

    @Resource
    UserService userService;

    public TablePage<User> list() {
        return null;
    }
}
