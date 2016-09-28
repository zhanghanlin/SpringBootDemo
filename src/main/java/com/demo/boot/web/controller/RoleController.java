package com.demo.boot.web.controller;

import com.demo.boot.business.PermissionService;
import com.demo.boot.business.RoleService;
import com.demo.boot.business.SysService;
import com.demo.boot.business.UserService;
import com.demo.boot.entity.Permission;
import com.demo.boot.entity.Role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Resource
    SysService sysService;

    @Resource
    PermissionService permissionService;

    @Resource
    RoleService roleService;

    @Resource
    UserService userService;

    @RequiresPermissions("role:view")
    @RequestMapping(value = {"/list", "/", ""}, method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("boot/role");
    }


    @RequiresPermissions("role:edit")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id) {
        ModelAndView model = new ModelAndView("boot/roleInput");
        Role role = roleService.get(id);
        List<Permission> perms = permissionService.getByRole(id);
        List<Permission> allPerm = permissionService.getAll();
        model.addObject("role", role);
        model.addObject("perms", perms);
        model.addObject("allPerm", allPerm);
        return model;
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(Role role, RedirectAttributes redirectAttributes) {
        try {
            sysService.saveRole(role);
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", "更新失败");
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("list"));
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "/assign/{id}", method = RequestMethod.GET)
    public ModelAndView assign(@PathVariable String id) {
        ModelAndView model = new ModelAndView("boot/roleAssign");
        Role role = roleService.get(id);
        model.addObject("role", role);
        model.addObject("user", userService.getByRole(id));
        model.addObject("notUser", userService.getByNotRole(id));
        return model;
    }

    /**
     * 分配角色到用户
     *
     * @param role
     * @param userIds
     * @return
     */
    @RequiresPermissions("role:edit")
    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public ModelAndView assign(Role role, String[] userIds) {
        sysService.assignUserToRole(role, userIds);
        return new ModelAndView(new RedirectView("list"));
    }
}
