package com.tistory.jaimemin.exception.api;

import com.tistory.jaimemin.exception.exception.UserException;
import com.tistory.jaimemin.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

    @GetMapping("/api2/members/{id}")
    public ApiExceptionController.MemberDto getmember(@PathVariable("id") String id) {
        if ("ex".equals(id)) {
            throw new RuntimeException("잘못된 사용자");
        } else if ("bad".equals(id)) {
            throw new IllegalArgumentException("잘못된 입력 값");
        } else if ("user-ex".equals(id)) {
            throw new UserException("사용자 오류");
        }

        return new ApiExceptionController.MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {

        private String memberId;

        private String name;
    }
}
