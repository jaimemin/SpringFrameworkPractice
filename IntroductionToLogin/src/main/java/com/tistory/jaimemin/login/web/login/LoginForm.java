package com.tistory.jaimemin.login.web.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
