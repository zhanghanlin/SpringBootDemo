package com.demo.boot.dict;

/**
 * 角色枚举
 */
public enum RoleEnum {
    ADMIN(4), NORMAL_USER(5);

    /**
     * 对应数据库角色Id
     */
    private Integer id;

    RoleEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
