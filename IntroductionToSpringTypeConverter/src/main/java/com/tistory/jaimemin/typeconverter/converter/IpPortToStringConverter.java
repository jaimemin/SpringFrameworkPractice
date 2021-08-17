package com.tistory.jaimemin.typeconverter.converter;

import com.tistory.jaimemin.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        log.info("convert source = {}", source);

        StringBuilder ipPortString = new StringBuilder();

        return ipPortString.append(source.getIp())
                .append(":")
                .append(source.getPort())
                .toString();
    }
}
