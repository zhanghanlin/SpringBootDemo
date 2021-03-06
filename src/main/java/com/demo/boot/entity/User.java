package com.demo.boot.entity;

import com.demo.boot.dict.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class User extends BaseEntity {

    private String userName;

    private String password;

    private String displayName;

    private String email;

    private String phone;

    private Integer status;

    private List<Role> roles = Lists.newArrayList();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public List<String> getRoleIds() {
        List<String> roleIds = Lists.newArrayList();
        for (Role role : roles) {
            roleIds.add(role.getId());
        }
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        List<String> _roleIds = getRoleIds();
        if (roles == null) roles = Lists.newArrayList();
        for (String rid : roleIds) {
            if (_roleIds.contains(rid)) {
                continue;
            }
            Role role = new Role();
            role.setId(rid);
            roles.add(role);
        }
    }

    public Set<String> getRolesKey() {
        Set<String> set = Sets.newHashSet();
        for (int i = 0; i < roles.size(); i++) {
            set.add(roles.get(i).getUniqueKey());
        }
        return set;
    }

    /**
     * 判断是否Admin
     *
     * @return
     */
    public boolean isAdmin() {
        return getRolesKey().contains(RoleEnum.ADMIN.getKey());
    }
}