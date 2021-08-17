package com.tistory.jaimemin.typeconverter;

import com.tistory.jaimemin.typeconverter.converter.IntegerToStringConverter;
import com.tistory.jaimemin.typeconverter.converter.IpPortToStringConverter;
import com.tistory.jaimemin.typeconverter.converter.StringToIntegerConverter;
import com.tistory.jaimemin.typeconverter.converter.StringToIpPortConverter;
import com.tistory.jaimemin.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // 컨버터가 포맷터보다 우선수위가 높음
        registry.addFormatter(new MyNumberFormatter());
    }
}
