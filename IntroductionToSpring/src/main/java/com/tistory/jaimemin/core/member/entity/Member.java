package com.tistory.jaimemin.core.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Member {

    private Long id;

    private String name;

    private Grade grade;
}
