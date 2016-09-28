package com.demo.boot;

import com.demo.boot.configure.listener.ApplicationListenerEnvironmentPrepared;
import com.demo.boot.configure.listener.ApplicationListenerFailed;
import com.demo.boot.configure.listener.ApplicationListenerPrepared;
import com.demo.boot.configure.listener.ApplicationListenerStarted;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
//spring能够扫描到我们自己编写的servlet和filter
@ServletComponentScan
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