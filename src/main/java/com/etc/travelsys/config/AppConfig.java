package com.etc.travelsys.config;

import com.etc.travelsys.components.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AppConfig implements WebMvcConfigurer {

    //统一视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //访问路径为http://localhost:8080/时，那么就是访问templates下的login文件夹下的login.html页面
        registry.addViewController("/").setViewName("login/login");
        registry.addViewController("/login.html").setViewName("login/login");
        registry.addViewController("/register.html").setViewName("login/register");
        registry.addViewController("/face_login.html").setViewName("login/face_login");
        registry.addViewController("/face_regist.html").setViewName("login/face_regist");
    }

    //配置显示图片路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandlers:url请求路径，自定义
        //addResourceHandlers:图片存放的真实路径
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:D:/Windows11/Desktop/upload/");
    }

    //配置用户登录的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //addInterceptor

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login.html","/register.html", "/user/login",
                        "/user/checkUserName","/user/register","/toPage","/face_login.html","/user/faceLogin",
                        "/webjars/**", "/js/**","/images/**","/img/**","/css/**","/face/**");
    }
}
