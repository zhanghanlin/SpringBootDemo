package com.demo.boot.controller;

import com.demo.boot.business.PermissionService;
import com.demo.boot.entity.Permission;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@EnableAutoConfiguration
@RestController
@RequestMapping("perm")
public class PermissionController {

    @Resource
    PermissionService permissionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView user() {
        List<Permission> list = Lists.newArrayList();
        List<Permission> sourceList = permissionService.getAll();
        Permission.sortList(list, sourceList, Permission.getRootId(), true);
        return new ModelAndView("boot/perm", "perm", list);
    }
}
