package com.demo.boot.configure.shiro;

import com.demo.boot.utils.EhcacheUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限制登录次数，如果5次出错，锁定1个小时
 */
public class LimitRetryHashedMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userName = (String) token.getPrincipal();
        //retry count + 1
        Object element = EhcacheUtils.getItem(userName);
        if (element == null) {
            EhcacheUtils.putItem(userName, 1);
            element = 0;
        } else {
            int count = Integer.parseInt(element.toString()) + 1;
            element = count;
            EhcacheUtils.putItem(userName, element);
        }
        AtomicInteger retryCount = new AtomicInteger(Integer.parseInt(element.toString()));
        if (retryCount.incrementAndGet() > 5) {
            //if retry count >5 throw
            throw new ExcessiveAttemptsException();
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry count
            EhcacheUtils.removeItem(userName);
        }
        return matches;
    }
}
