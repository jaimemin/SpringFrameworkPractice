package com.tistory.jaimemin.typeconverter.converter;

import com.tistory.jaimemin.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");

        assertThat(result).isEqualTo(10);
    }

    @Test
    void IntegerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);

        assertThat(result).isEqualTo("10");
    }

    @Test
    void stringToIpPort() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);

        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void ipPortToString() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort ipPort = converter.convert("127.0.0.1:8080");

        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

    @Test
    void birthdayToString() {
        BirthdayToStringConverter converter = new BirthdayToStringConverter();
        String birthday = converter.convert(LocalDate.of(2021, 8, 11));

        assertThat(birthday).isEqualTo("2021.08.11");
    }

    @Test
    void stringToBirthday() {
        StringToBirthdayConverter converter = new StringToBirthdayConverter();
        LocalDate birthday = converter.convert("2021.08.11");

        assertThat(birthday).isEqualTo(LocalDate.of(2021, 8, 11));
    }
}
