package com.demo.boot.configure.boot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring boot上下文context创建完成
 * 在刷新开始之前，beans加载之后被发送
 * 此时spring中的bean是没有完全加载完成的
 */
public class ApplicationListenerPrepared implements ApplicationListener<ApplicationPreparedEvent> {

    static final Logger LOG = LoggerFactory.getLogger(ApplicationListenerPrepared.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        passContextInfo(context);
        LOG.info("ApplicationPreparedEvent");
    }

    /**
     * 传递上下文
     *
     * @param context
     */
    private void passContextInfo(ApplicationContext context) {
        // 上下文处理
    }
}
