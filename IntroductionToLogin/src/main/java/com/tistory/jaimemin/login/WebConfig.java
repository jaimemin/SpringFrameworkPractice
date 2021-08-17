package com.tistory.jaimemin.login;

import com.tistory.jaimemin.login.web.argumentresolver.LoginMemberArgumentResolver;
import com.tistory.jaimemin.login.web.filter.LogFilter;
import com.tistory.jaimemin.login.web.filter.LoginCheckFilter;
import com.tistory.jaimemin.login.web.interceptor.LogInterceptor;
import com.tistory.jaimemin.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**"
                        , "/*.ico"
                        , "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/"
                        , "/members/add"
                        , "/login"
                        , "/logout"
                        , "/css/**"
                        , "/*.ico"
                        , "/error");
    }

    // @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new LogFilter());
        filterRegistration.setOrder(1);
        filterRegistration.addUrlPatterns("/*");

        return filterRegistration;
    }

    // @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new LoginCheckFilter());
        filterRegistration.setOrder(2);
        filterRegistration.addUrlPatterns("/*");

        return filterRegistration;
    }
}
