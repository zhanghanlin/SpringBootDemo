package com.demo.boot.configure.boot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Iterator;

/**
 * spring boot 配置环境事件监听
 * 在上下文被创建之前，应用环境被已知的上下文环境中使用时被发送
 */
public class ApplicationListenerEnvironmentPrepared implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    static final Logger LOG = LoggerFactory.getLogger(ApplicationListenerEnvironmentPrepared.class);

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        MutablePropertySources mps = environment.getPropertySources();
        if (mps != null) {
            Iterator<PropertySource<?>> iterator = mps.iterator();
            while (iterator.hasNext()) {
                PropertySource<?> ps = iterator.next();
                LOG.debug("ps.getName:{};ps.getSource:{};ps.getClass:{}", ps.getName(), ps.getSource(), ps.getClass());
            }
        }
        LOG.info("ApplicationEnvironmentPreparedEvent");
    }
}
