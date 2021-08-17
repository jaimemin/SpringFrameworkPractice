package com.tistory.jaimemin.servlet.web.frontcontroller.v3.controller;

import com.tistory.jaimemin.servlet.domain.member.Member;
import com.tistory.jaimemin.servlet.domain.member.repository.MemberRepository;
import com.tistory.jaimemin.servlet.web.frontcontroller.ModelView;
import com.tistory.jaimemin.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members);

        return modelView;
    }
}
