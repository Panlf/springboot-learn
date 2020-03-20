package com.plf.learn.thread.interceptor;

import com.plf.learn.thread.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * 请求->tomcat容器->filter->servlet->inteceptor->controller
 * 验证User
 * @author Panlf
 * @date 2020/3/20
 */
@Component
@Slf4j
public class AuthUserInterceptor implements HandlerInterceptor {

    /**
     *该方法将在请求处理之前进行调用，只有该方法返回true，才会继续执行后续的Interceptor和Controller，
     * 当返回值为true 时就会继续调用下一个Interceptor的preHandle 方法，
     * 如果已经是最后一个Interceptor的时候就会是调用当前请求的Controller方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI =	request.getRequestURI();
        log.info("start AuthUserInterceptor,request uri : {}",requestURI);
        /**
         * 对静态资源和错误页进行放行
         */
        if (requestURI.startsWith("/static") || requestURI.startsWith("/error")) {
            return true;
        }
        HttpSession session = request.getSession(true);
        User user =  (User)session.getAttribute("user");
        if (user != null) {
            log.info("user info : {}",user);
            UserContextHolder.setUser(user);
        }
        return true;
    }

    /**
     * 该方法将在请求处理之后，DispatcherServlet进行视图返回渲染之前进行调用，
     * 可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行，该方法将在整个请求结束之后，
     * 也就是在DispatcherServlet 渲染了对应的视图之后执行。用于进行资源清理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.remove();
    }
}
