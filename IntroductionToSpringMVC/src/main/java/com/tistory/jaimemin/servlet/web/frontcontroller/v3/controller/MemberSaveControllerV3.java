package com.tistory.jaimemin.servlet.web.frontcontroller.v3.controller;

import com.tistory.jaimemin.servlet.domain.member.Member;
import com.tistory.jaimemin.servlet.domain.member.repository.MemberRepository;
import com.tistory.jaimemin.servlet.web.frontcontroller.ModelView;
import com.tistory.jaimemin.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = Member.builder()
                .username(username)
                .age(age)
                .build();
        memberRepository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);

        return modelView;
    }
}
