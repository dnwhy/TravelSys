package com.etc.travelsys.components;

import com.etc.travelsys.model.entity.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    //这个方法在业务处理器处理请求之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从session中获取UserEntity对象
        UserEntity user = (UserEntity) request.getSession().getAttribute("USER");
        //判断是否登录  登录--放行  return true  没有登录 ------  跳转到登录页面
        if (user == null){
            response.sendRedirect("/login.html");
            return false;
        }
        //true----放行    false----不放行
        return true;
    }

    //这个方法在当前请求进行处理之后，也就是说controller方法调用之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //这个方法是需要当前对应的拦截器的preHandler方法返回值为ture时才执行。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
