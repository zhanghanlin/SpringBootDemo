package com.demo.boot.web.controller;

import com.demo.boot.business.PermissionService;
import com.demo.boot.entity.Permission;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("perm")
public class PermissionController {

    @Resource
    PermissionService permissionService;

    @RequiresPermissions("perm:view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        List<Permission> list = Lists.newArrayList();
        List<Permission> sourceList = permissionService.getAll();
        Permission.sortList(list, sourceList, Permission.getRootId(), true);
        return new ModelAndView("boot/perm", "perms", list);
    }

    @RequiresPermissions("perm:edit")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id) {
        ModelAndView model = new ModelAndView("boot/permInput");
        Permission permission = permissionService.get(id);
        model.addObject("perm", permission);
        model.addObject("allPerm", permissionService.getAll());
        return model;
    }

    @RequiresPermissions("perm:edit")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(Permission permission) {
        ModelAndView model = new ModelAndView(new RedirectView("/perm/list"));
        permissionService.update(permission);
        return model;
    }
}
