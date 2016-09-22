package com.demo.boot.configure.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * spring boot启动异常时执行事件
 * 在异常发生时，最好是添加虚拟机对应的钩子进行资源的回收与释放，能友善的处理异常信息
 */
public class ApplicationListenerFailed implements ApplicationListener<ApplicationFailedEvent> {

    static final Logger LOG = LoggerFactory.getLogger(ApplicationListenerFailed.class);

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        Throwable throwable = event.getException();
        handleThrowable(throwable);
        LOG.info("ApplicationFailedEvent");
    }

    /**
     * 处理异常
     *
     * @param throwable
     */
    private void handleThrowable(Throwable throwable) {
        // 异常处理
    }
}
