package com.demo.boot.configure.shiro;

import com.demo.boot.utils.CacheUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

import static com.demo.boot.dict.Boot.LOGIN_FAIL_COUNT_KEY;

/**
 * 限制登录次数，如果5次出错，锁定1个小时
 */
public class LimitRetryHashedMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userName = (String) token.getPrincipal();
        String key = LOGIN_FAIL_COUNT_KEY + userName;
        //retry count + 1
        Object element = CacheUtils.get(key);
        if (element == null) {
            CacheUtils.put(key, 1);
            element = 0;
        } else {
            int count = Integer.parseInt(element.toString()) + 1;
            element = count;
            CacheUtils.put(key, element);
        }
        AtomicInteger retryCount = new AtomicInteger(Integer.parseInt(element.toString()));
        if (retryCount.incrementAndGet() > 5) {
            //if retry count >5 throw
            throw new ExcessiveAttemptsException();
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry count
            CacheUtils.remove(key);
        }
        return matches;
    }
}
