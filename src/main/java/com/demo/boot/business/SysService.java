package com.demo.boot.business;

import com.demo.boot.dict.RoleEnum;
import com.demo.boot.entity.Permission;
import com.demo.boot.entity.User;
import com.demo.boot.entity.UserRole;
import com.demo.boot.vo.Register;
import com.demo.boot.vo.menu.MenuNode;
import com.demo.boot.vo.menu.MenuTree;
import com.google.common.collect.Lists;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysService {

    @Resource
    PermissionService permissionService;

    @Resource
    UserService userService;

    @Resource
    UserRoleService userRoleService;

    /**
     * 注册
     *
     * @param register
     * @return
     */
    public int register(Register register) {
        int res = 0;
        User user = new User();
        user.setUserName(register.getUserName());
        user.setPassword(new SimpleHash("md5", register.getPassword(), null, 2).toHex());
        user.setDisplayName(register.getDisplayName());
        userService.insert(user);
        if (user.getId() > 0) {
            UserRole userRole = new UserRole();
            userRole.setUserId(res);
            userRole.setRoleId(RoleEnum.NORMAL_USER.getId());
            res = userRoleService.insert(userRole);
        }
        return res;
    }

    /**
     * 菜单树结构
     *
     * @return
     */
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
