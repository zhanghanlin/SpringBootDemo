package com.demo.boot.utils;

import java.util.UUID;

/**
 * ID 生成器
 */
public class IdGen {
    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
