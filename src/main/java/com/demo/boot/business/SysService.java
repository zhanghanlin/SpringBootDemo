package com.demo.boot.business;

import com.demo.boot.dict.RoleEnum;
import com.demo.boot.entity.Permission;
import com.demo.boot.entity.Role;
import com.demo.boot.entity.User;
import com.demo.boot.utils.HashPassword;
import com.demo.boot.utils.StringUtils;
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
    RoleService roleService;

    @Resource
    UserService userService;

    /**
     * 注册
     *
     * @param register
     * @return
     */
    public void register(Register register) {
        User user = new User();
        user.setUserName(register.getUserName());
        user.setPassword(HashPassword.pwdHash(register.getPassword()));
        user.setDisplayName(register.getDisplayName());
        List<String> roleIds = Lists.newArrayList();
        roleIds.add(RoleEnum.NORMAL_USER.getId());
        user.setRoleIds(roleIds);
        saveUser(user);
    }

    /**
     * 保存/更新用户
     *
     * @param user
     */
    @Transactional
    public void saveUser(User user) {
        if (StringUtils.isBlank(user.getId())) {
            userService.insert(user);
        } else {
            userService.update(user);
        }
        userService.deleteUserRole(user.getId());
        userService.insertUserRole(user);
    }

    /**
     * 更新角色
     *
     * @param role
     */
    @Transactional
    public void saveRole(Role role) {
        if (StringUtils.isBlank(role.getId())) {
            roleService.insert(role);
        } else {
            roleService.update(role);
        }
        roleService.deleteRolePermission(role.getId());
        if (role.getPermIdList() != null && !role.getPermIdList().isEmpty())
            roleService.insertRolePermission(role);
        // 清除用户角色缓存
        UserUtils.Cache.removeCache(UserUtils.CACHE_ROLE_LIST);
    }

    /**
     * 菜单树结构
     *
     * @return
     */
    public List<MenuNode> tree() {
        List<Permission> perms = UserUtils.getPerms();
        List<MenuNode> nodes = Lists.newArrayList();
        for (Permission p : perms) {
            MenuNode node = new MenuNode(p);
            nodes.add(node);
        }
        MenuTree tree = new MenuTree(nodes);
        return tree.buildTree();
    }

    /**
     * 给用户添加角色
     *
     * @param role
     * @param user
     * @return
     */
    public User assignUserToRole(Role role, User user) {
        if (user == null) {
            return null;
        }
        List<String> roleIds = user.getRoleIds();
        if (roleIds.contains(role.getId())) {
            return null;
        }
        roleIds.add(role.getId());
        user.setRoleIds(roleIds);
        saveUser(user);
        return user;
    }
}
