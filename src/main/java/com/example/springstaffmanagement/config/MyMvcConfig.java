package com.example.springstaffmanagement.config;

import com.example.springstaffmanagement.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("signin");
        registry.addViewController("/signin.html").setViewName("signin");
        registry.addViewController("/index.html").setViewName("index");


    }

    //自定义的国际化组件就生效了
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
        //return new AcceptHeaderLocaleResolver();
    }

    //配置加载拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**").
//                excludePathPatterns("/signin.html","/","/user/login","/css/**","/js/**","/font/**","/image/**","/picture/**","/webfonts/**");
//    }




}
