package com.demo.boot.dict;

/**
 * 角色枚举
 */
public enum RoleEnum {
    ADMIN("1", "admin"), NORMAL_USER("2", "normal_user");

    /**
     * 对应数据库角色Id
     */
    private String id;

    private String key;

    RoleEnum(String id, String key) {
        this.id = id;
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }
}
