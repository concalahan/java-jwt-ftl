package com.example.jwt.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.jwt.jwt.auth.JwtFilter;

@Configuration
public class AppConfig {
//	@Value("${services.auth}")
//    private String authService;

    @Bean
    public FilterRegistrationBean jwtFilter() {
    	System.out.println("vao 33");
        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<JwtFilter>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.setInitParameters(Collections.singletonMap("services.auth", "${services.auth}"));
        registrationBean.addUrlPatterns("/protected-resource");

        return registrationBean;
    }
}