package com.demo.boot.configure.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * spring boot启动开始时执行的事件
 * 在任何处理之前，程序开始运行时被发送，初始化和自定义注册监听事件除外
 */
public class ApplicationListenerStarted implements ApplicationListener<ApplicationStartedEvent> {

    static final Logger LOG = LoggerFactory.getLogger(ApplicationListenerStarted.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        SpringApplication app = event.getSpringApplication();
        app.setBannerMode(Banner.Mode.OFF);// 不显示banner信息
        LOG.info("ApplicationListenerStarted");
    }
}
