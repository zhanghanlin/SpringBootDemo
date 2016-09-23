package com.demo.boot.web.controller;

import com.demo.boot.business.UserService;
import com.demo.boot.entity.User;
import com.demo.boot.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @RequiresPermissions("user:view")
    @RequestMapping(value = {"/list", "/", ""}, method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("boot/user");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id) {
        User user = UserUtils.get(id);
        return new ModelAndView("boot/userInput", "user", user);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(User user, RedirectAttributes redirectAttributes) {
        try {
            user.setPhone(user.getPhone().replaceAll("-", ""));
            userService.update(user);
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", "更新失败");
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("list"));
    }
}
