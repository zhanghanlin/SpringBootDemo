package com.demo.boot.web.controller;

import com.demo.boot.business.PermissionService;
import com.demo.boot.entity.Permission;
import com.demo.boot.utils.StringUtils;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(Permission permission) {
        if (permission.getParent() == null || StringUtils.isBlank(permission.getParent().getId())) {
            permission.setParent(new Permission(Permission.getRootId()));
        }
        Permission parent = permissionService.get(permission.getParent().getId());
        permission.setParent(parent);
        List<Permission> permList = permissionService.getAll();
        // 获取排序号，最末节点排序号+30
        if (StringUtils.isBlank(permission.getId())) {
            List<Permission> list = Lists.newArrayList();
            Permission.sortList(list, permList, parent.getId(), false);
            if (list.size() > 0) {
                permission.setWeight(list.get(list.size() - 1).getWeight() + 1);
            } else {
                Permission.sortList(list, permList, parent.getParent().getId(), false);
                if (list.size() > 0) {
                    permission.setWeight(list.get(list.size() - 1).getWeight() * 100 + 1);
                }
            }
        } else {
            permission = permissionService.get(permission.getId());
        }
        ModelAndView model = new ModelAndView("boot/permInput");
        model.addObject("perm", permission);
        model.addObject("allPerm", permList);
        return model;
    }

    @RequiresPermissions("perm:edit")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Permission permission) {
        ModelAndView model = new ModelAndView(new RedirectView("/perm/list"));
        permissionService.update(permission);
        return model;
    }
}
