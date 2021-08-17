package com.tistory.jaimemin.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

@Slf4j
public class StringToBirthdayConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        log.info("birthday: {}", source);

        String[] numbers = source.split("\\.");
        int year = Integer.parseInt(numbers[0]);
        int month = Integer.parseInt(numbers[1]);
        int day = Integer.parseInt(numbers[2]);

        return LocalDate.of(year, month, day);
    }
}
