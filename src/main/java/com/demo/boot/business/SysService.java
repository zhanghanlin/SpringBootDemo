package com.demo.boot.business;

import com.demo.boot.entity.Permission;
import com.demo.boot.vo.menu.MenuNode;
import com.demo.boot.vo.menu.MenuTree;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysService {

    @Resource
    PermissionService permissionService;

    @RequestMapping("menu")
    @ResponseBody
    public List<MenuNode> menu() {
        List<Permission> perms = permissionService.getAll();
        List<MenuNode> nodes = Lists.newArrayList();
        for (Permission p : perms) {
            MenuNode node = new MenuNode(p);
            nodes.add(node);
        }
        MenuTree tree = new MenuTree(nodes);
        return tree.buildTree();
    }
}
