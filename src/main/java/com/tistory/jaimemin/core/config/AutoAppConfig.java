package com.tistory.jaimemin.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// AppConfig, TestConfig 등록을 방지하기 위해 excludeFilters
@Configuration
@ComponentScan(
        basePackages = "com.tistory.jaimemin.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
