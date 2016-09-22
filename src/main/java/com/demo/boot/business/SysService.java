package com.demo.boot.business;

import com.demo.boot.dict.RoleEnum;
import com.demo.boot.entity.Permission;
import com.demo.boot.entity.User;
import com.demo.boot.entity.UserRole;
import com.demo.boot.utils.HashPassword;
import com.demo.boot.utils.UserUtils;
import com.demo.boot.web.vo.Register;
import com.demo.boot.web.vo.menu.MenuNode;
import com.demo.boot.web.vo.menu.MenuTree;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysService {

    static final Logger LOG = LoggerFactory.getLogger(SysService.class);

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
    @Transactional
    public void register(Register register) {
        try {
            User user = new User();
            user.setUserName(register.getUserName());
            user.setPassword(HashPassword.pwdHash(register.getPassword()));
            user.setDisplayName(register.getDisplayName());
            userService.insert(user);
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(RoleEnum.NORMAL_USER.getId());
            userRoleService.insert(userRole);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 菜单树结构
     *
     * @return
     */
    public List<MenuNode> menu() {
        List<Permission> perms = UserUtils.getPerms();
        List<MenuNode> nodes = Lists.newArrayList();
        for (Permission p : perms) {
            MenuNode node = new MenuNode(p);
            nodes.add(node);
        }
        MenuTree tree = new MenuTree(nodes);
        return tree.buildTree();
    }
}
