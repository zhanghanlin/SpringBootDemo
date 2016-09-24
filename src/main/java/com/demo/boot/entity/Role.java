package com.demo.boot.entity;

import com.demo.boot.utils.StringUtils;
import com.google.common.collect.Lists;

import java.util.List;

public class Role extends BaseEntity {

    private String name;

    private String note;

    private String uniqueKey;

    private Integer status;

    private List<Permission> perms = Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey == null ? null : uniqueKey.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Permission> getPerms() {
        return perms;
    }

    public void setPerms(List<Permission> perms) {
        this.perms = perms;
    }

    /**
     * 根据权限集合得到权限Id集合
     *
     * @return
     */
    public List<String> getPermIdList() {
        List<String> permIdList = Lists.newArrayList();
        for (Permission p : perms) {
            permIdList.add(p.getId());
        }
        return permIdList;
    }

    /**
     * 根据Id拼装权限对象 写入权限集合
     *
     * @param perms
     */
    public void setPermIdList(List<String> perms) {
        this.perms = Lists.newArrayList();
        for (String id : perms) {
            Permission p = new Permission();
            p.setId(id);
            this.perms.add(p);
        }
    }

    /**
     * 获取逗号分割的权限Id集合
     *
     * @return
     */
    public String getPermIds() {
        return StringUtils.join(getPermIdList(), ",");
    }

    /**
     * 写入权限Id串
     *
     * @param permIds
     */
    public void setPermIds(String permIds) {
        if (StringUtils.isNotBlank(permIds)) {
            String[] ids = StringUtils.split(permIds, ",");
            setPermIdList(Lists.newArrayList(ids));
        }
    }
}