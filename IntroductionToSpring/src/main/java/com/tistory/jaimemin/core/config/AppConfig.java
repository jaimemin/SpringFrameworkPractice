package com.tistory.jaimemin.core.config;


import com.tistory.jaimemin.core.discount.DiscountPolicy;
import com.tistory.jaimemin.core.discount.RateDiscountPolicy;
import com.tistory.jaimemin.core.member.repository.MemberRepository;
import com.tistory.jaimemin.core.member.repository.MemoryMemberRepository;
import com.tistory.jaimemin.core.member.service.MemberService;
import com.tistory.jaimemin.core.member.service.MemberServiceImpl;
import com.tistory.jaimemin.core.order.service.OrderService;
import com.tistory.jaimemin.core.order.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * DIP 완성
 * 구성 영역과 사용 영역 분리
 * ApplicationContext가 Bean들을 관리
 */
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository()
                , discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
