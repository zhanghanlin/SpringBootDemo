package com.demo.boot.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 密码加密
 */
public class HashPassword {

    /**
     * 加密算法
     *
     * @param algorithmName
     * @param source
     * @param salt
     * @param hashIterations
     * @return
     */
    public static String simpleHash(String algorithmName, Object source, Object salt, int hashIterations) {
        return new SimpleHash(algorithmName, source, salt, hashIterations).toHex();
    }

    /**
     * Shiro使用 加密算法
     *
     * @param source
     * @return
     */
    public static String pwdHash(String source) {
        return simpleHash("md5", source, null, 2);
    }
}
