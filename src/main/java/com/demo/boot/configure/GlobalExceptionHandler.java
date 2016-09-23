package com.demo.boot.configure;

import org.apache.ibatis.javassist.NotFoundException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认异常处理
     *
     * @param e
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        e.printStackTrace();
        return new ModelAndView("error/500");
    }

    /**
     * 权限异常
     *
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView unauthorizedException() {
        return new ModelAndView("error/403");
    }

    /**
     * 404异常
     *
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundException() {
        return new ModelAndView("error/404");
    }
}
