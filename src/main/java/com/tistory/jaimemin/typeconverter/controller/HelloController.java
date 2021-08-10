package com.tistory.jaimemin.typeconverter.controller;

import com.tistory.jaimemin.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class HelloController {
    
    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data"); // 문자 타입 조회
        Integer intValue = Integer.valueOf(data); // 숫자 타입으로 변환

        log.info("intValue = {}", intValue);

        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        log.info("data = {}", data);

        return "ok";
    }

    // Converter 동작 잘하는 것을 확인 가능
    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        log.info("ipPort ip = {}, port = {}", ipPort.getIp(), ipPort.getPort());

        return "ok";
    }
}
