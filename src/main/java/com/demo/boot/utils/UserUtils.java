package com.demo.boot.utils;

import com.demo.boot.business.PermissionService;
import com.demo.boot.business.RoleService;
import com.demo.boot.business.UserService;
import com.demo.boot.entity.Permission;
import com.demo.boot.entity.Role;
import com.demo.boot.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

import static com.demo.boot.utils.UserUtils.Cache.getCache;
import static com.demo.boot.utils.UserUtils.Cache.removeCache;

/**
 * 用户工具类
 */
public class UserUtils {

    private static UserService userService = SpringUtils.getBean(UserService.class);
    private static RoleService roleService = SpringUtils.getBean(RoleService.class);
    private static PermissionService permissionService = SpringUtils.getBean(PermissionService.class);

    public static final String USER_CACHE = "userCache";
    public static final String USER_CACHE_ID_ = "id_";
    public static final String USER_CACHE_LOGIN_NAME_ = "ln";

    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_PERM_LIST = "permList";

    /**
     * 根据ID获取用户
     *
     * @param id
     * @return 取不到返回null
     */
    public static User get(String id) {
        User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
        if (user == null) {
            user = userService.get(id);
            if (user == null) {
                return null;
            }
            user.setRoles(roleService.getRoleByUser(user.getId()));
            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUserName(), user);
        }
        return user;
    }

    /**
     * 根据用户名获取用户
     *
     * @param userName
     * @return 取不到返回null
     */
    public static User getByUserName(String userName) {
        User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + userName);
        if (user == null) {
            user = userService.getByUserName(userName);
            if (user == null) {
                return null;
            }
            user.setRoles(roleService.getRoleByUser(user.getId()));
            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUserName(), user);
        }
        return user;
    }

    /**
     * 清除当前用户缓存
     */
    public static void clearCache() {
        removeCache(CACHE_ROLE_LIST);
        removeCache(CACHE_PERM_LIST);
        UserUtils.clearCache(getUser());
    }

    /**
     * 清除指定用户缓存
     *
     * @param user
     */
    public static void clearCache(User user) {
        CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUserName());
    }

    /**
     * 获取当前用户
     *
     * @return 取不到返回 new User()
     */
    public static User getUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null || subject.getPrincipal() == null) {
            return null;
        }
        String userName = subject.getPrincipal().toString();
        User user = userService.getByUserName(userName);
        if (user != null) {
            return user;
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new User();
    }

    /**
     * 获取当前用户角色
     *
     * @return
     */
    public static List<Role> getRoles() {
        List<Role> roles = (List<Role>) getCache(CACHE_ROLE_LIST);
        if (roles == null || !roles.isEmpty()) {
            User user = getUser();
            if (user.isAdmin()) {
                roles = roleService.getAll();
            } else {
                roles = roleService.getRoleByUser(user.getId());
            }
            Cache.putCache(CACHE_ROLE_LIST, roles);
        }
        return roles;
    }

    /**
     * 用户权限列表
     *
     * @return
     */
    public static List<Permission> getPerms() {
        List<Permission> perms = (List<Permission>) getCache(CACHE_PERM_LIST);
        if (perms == null || perms.isEmpty()) {
            User user = getUser();
            if (user.isAdmin()) {
                perms = permissionService.getAll();
            } else {
                perms = permissionService.getPermissionByUser(user.getId());
            }
            Cache.putCache(CACHE_PERM_LIST, perms);
        }
        return perms;
    }

    static class Cache {
        public static Session getSession() {
            try {
                Subject subject = SecurityUtils.getSubject();
                Session session = subject.getSession(false);
                if (session == null) {
                    session = subject.getSession();
                }
                if (session != null) {
                    return session;
                }
            } catch (InvalidSessionException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Object getCache(String key) {
            return getCache(key, null);
        }

        public static Object getCache(String key, Object defaultValue) {
            Object obj = getSession().getAttribute(key);
            return obj == null ? defaultValue : obj;
        }

        public static void putCache(String key, Object value) {
            getSession().setAttribute(key, value);
        }

        public static void removeCache(String key) {
            getSession().removeAttribute(key);
        }
    }
}
