package com.tistory.jaimemin.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

@Slf4j
public class BirthdayToStringConverter implements Converter<LocalDate, String> {

    @Override
    public String convert(LocalDate source) {
        log.info("{}, {}, {}", source.getYear(), source.getMonthValue(), source.getDayOfMonth());

        StringBuilder birthday = new StringBuilder();
        birthday.append(source.getYear())
                .append(".")
                .append(String.format("%02d", source.getMonthValue()))
                .append(".")
                .append(String.format("%02d", source.getDayOfMonth()));

        return birthday.toString();
    }
}
