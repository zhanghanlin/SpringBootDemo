package com.demo.boot.configure.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 使用注解标注过滤器@WebFilter 将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 */
@WebFilter(filterName = "XSSFilter", urlPatterns = "/*")
public class XSSFilter implements Filter {

    static final Logger LOG = LoggerFactory.getLogger(XSSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("XSSFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new XSSRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }

    @Override
    public void destroy() {
        LOG.info("XSSFilter Destroy");
    }
}
