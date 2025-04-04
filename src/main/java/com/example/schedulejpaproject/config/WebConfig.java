package com.example.schedulejpaproject.config;

import com.example.schedulejpaproject.auth.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 웹 설정을 위한 설정 클래스입니다.
 * 필터 설정 등을 Bean으로 등록합니다.
 */
@Configuration
public class WebConfig {

    /**
     * 웹 설정을 위한 설정 클래스입니다.
     * 필터 설정 등을 Bean으로 등록합니다.
     */
    @Bean
    public FilterRegistrationBean<Filter> loginFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}