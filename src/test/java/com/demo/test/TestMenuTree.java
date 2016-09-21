package com.demo.test;

import com.demo.boot.Application;
import com.demo.boot.business.PermissionService;
import com.demo.boot.entity.Permission;
import com.demo.boot.vo.menu.MenuNode;
import com.demo.boot.vo.menu.MenuTree;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

// SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringJUnit4ClassRunner.class)
// 指定我们SpringBoot工程的Application启动类
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TestMenuTree {

    @Resource
    PermissionService permissionService;

    @Test
    public void menu() {
        List<Permission> perms = permissionService.getAll();
        List<MenuNode> nodes = Lists.newArrayList();
        for (Permission p : perms) {
            MenuNode node = new MenuNode(p);
            nodes.add(node);
        }
        MenuTree tree = new MenuTree(nodes);
        tree.buildTree();
        System.out.println("done");
    }
}
