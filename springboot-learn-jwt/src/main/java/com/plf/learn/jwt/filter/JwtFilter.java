package com.plf.learn.jwt.filter;

import com.plf.learn.jwt.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 需要授权的访问地址都可以移到这里来统一处理
 * @author Panlf
 * @date 2019/5/14
 */
@WebFilter("/*")
@Component
@Slf4j
public class JwtFilter implements Filter{

    /**
     *
     * 需要验证的名单
     */
    private static final List<String> NEED_AUTH_LIST = Arrays.asList("/hello");

    private static final String JWT_HEADER_NAME = "Authorization";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String token = httpServletRequest.getHeader(JWT_HEADER_NAME);
        String url = httpServletRequest.getRequestURI();
        log.info("当前访问地址:{}",url);
        if(NEED_AUTH_LIST.contains(httpServletRequest.getRequestURI())){
             log.info("进入验证名单");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
