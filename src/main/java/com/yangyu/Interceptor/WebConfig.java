package com.yangyu.Interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*
//拦截器配置
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
           registry.addInterceptor(new Logininterceptor())
                   .addPathPatterns("/admin/**")
                   .excludePathPatterns("/admin")
                   .excludePathPatterns("/admin/login");
    }
}
*/