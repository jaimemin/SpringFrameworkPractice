package com.tistory.jaimemin.core;

import com.tistory.jaimemin.core.config.AppConfig;
import com.tistory.jaimemin.core.member.entity.Grade;
import com.tistory.jaimemin.core.member.entity.Member;
import com.tistory.jaimemin.core.member.service.MemberService;
import com.tistory.jaimemin.core.member.service.MemberServiceImpl;
import com.tistory.jaimemin.core.order.entity.Order;
import com.tistory.jaimemin.core.order.service.OrderService;
import com.tistory.jaimemin.core.order.service.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService
                = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService
                = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = Member.builder()
                .id(1L)
                .name("memberA")
                .grade(Grade.VIP)
                .build();
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }

}
