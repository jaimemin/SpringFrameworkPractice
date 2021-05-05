package com.tistory.jaimemin.core;

import com.tistory.jaimemin.core.config.AppConfig;
import com.tistory.jaimemin.core.member.entity.Grade;
import com.tistory.jaimemin.core.member.entity.Member;
import com.tistory.jaimemin.core.member.service.MemberService;
import com.tistory.jaimemin.core.member.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService
                = applicationContext.getBean("memberService", MemberService.class);

        Member member = Member.builder()
                .id(1L)
                .name("memberA")
                .grade(Grade.VIP)
                .build();
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);
        System.out.println("new member: " + member.getName());
        System.out.println("found member: " + foundMember.getName());
    }

}
