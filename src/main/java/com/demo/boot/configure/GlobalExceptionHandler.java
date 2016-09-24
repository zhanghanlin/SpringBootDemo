package com.demo.boot.configure;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限异常
     *
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView unauthorizedException(Exception e) {
        LOG.info(e.getMessage());
        return new ModelAndView("error/403");
    }
}
