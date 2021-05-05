package com.tistory.jaimemin.core.config;


import com.tistory.jaimemin.core.discount.DiscountPolicy;
import com.tistory.jaimemin.core.discount.FixDiscountPolicy;
import com.tistory.jaimemin.core.discount.RateDiscountPolicy;
import com.tistory.jaimemin.core.member.repository.MemoryMemberRepository;
import com.tistory.jaimemin.core.member.service.MemberService;
import com.tistory.jaimemin.core.member.service.MemberServiceImpl;
import com.tistory.jaimemin.core.order.service.OrderService;
import com.tistory.jaimemin.core.order.service.OrderServiceImpl;

/*
 * DIP 완성
 * 구성 영역과 사용 영역 분리
 */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository()
                , getDiscountPolicy());
    }

    private MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    private DiscountPolicy getDiscountPolicy() {
        return new RateDiscountPolicy();
    }
}
