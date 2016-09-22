package com.demo.boot.dict;

/**
 * 角色枚举
 */
public enum RoleEnum {
    ADMIN("1"), NORMAL_USER("2");

    /**
     * 对应数据库角色Id
     */
    private String id;

    RoleEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
