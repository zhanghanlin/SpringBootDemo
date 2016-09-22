package com.demo.boot;

import com.demo.boot.configure.listener.ApplicationListenerEnvironmentPrepared;
import com.demo.boot.configure.listener.ApplicationListenerFailed;
import com.demo.boot.configure.listener.ApplicationListenerPrepared;
import com.demo.boot.configure.listener.ApplicationListenerStarted;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
//启动spring boot内置的自动配置
@EnableAutoConfiguration
//spring能够扫描到我们自己编写的servlet和filter
@ServletComponentScan
// 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableTransactionManagement
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new ApplicationListenerStarted());
        application.addListeners(new ApplicationListenerEnvironmentPrepared());
        application.addListeners(new ApplicationListenerPrepared());
        application.addListeners(new ApplicationListenerFailed());
        application.run(args);
    }
}