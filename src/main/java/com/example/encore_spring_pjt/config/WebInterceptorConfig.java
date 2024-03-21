package com.example.encore_spring_pjt.config;

import com.example.encore_spring_pjt.interceptor.BoardInterceptor;
import com.example.encore_spring_pjt.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // login interceptor
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/index.hanwha");

        // board interceptor
        registry.addInterceptor(new BoardInterceptor())
                .addPathPatterns("/board/**");

    }

}
