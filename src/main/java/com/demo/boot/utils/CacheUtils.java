package com.demo.boot.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
 * ehcache的缓存工具类
 */
public class CacheUtils {

    private static final CacheManager cacheManager = CacheManager.getInstance();

    private static final String SYS_CACHE = "sysCache";

    /**
     * 获取SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    /**
     * 写入SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    /**
     * 从SYS_CACHE缓存中移除
     *
     * @param key
     * @return
     */
    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * 写入缓存
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    /**
     * 从缓存中移除
     *
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 获得一个Cache，没有则创建一个。
     *
     * @param cacheName
     * @return
     */
    private static Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            //创建ehcache缓存，创建之后的有效期是1小时
            cache = new Cache(new CacheConfiguration(cacheName, 5000)
                    .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO)
                    .timeoutMillis(300)
                    .timeToLiveSeconds(60 * 60));
            cacheManager.addCache(cache);
        }
        return cache;
    }
}
