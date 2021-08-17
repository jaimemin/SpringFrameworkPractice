package com.tistory.jaimemin.login.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long id;

    @NotEmpty
    private String loginId; // 로그인 ID

    @NotEmpty
    private String name; // 사용자 이름

    @NotEmpty
    private String password; // 비밀번호
}
