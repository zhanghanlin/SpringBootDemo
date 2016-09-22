package com.demo.boot.web.controller;

import com.demo.boot.business.PermissionService;
import com.demo.boot.entity.Permission;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("perm")
public class PermissionController {

    @Resource
    PermissionService permissionService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        List<Permission> list = Lists.newArrayList();
        List<Permission> sourceList = permissionService.getAll();
        Permission.sortList(list, sourceList, Permission.getRootId(), true);
        return new ModelAndView("boot/perm", "perms", list);
    }
}
