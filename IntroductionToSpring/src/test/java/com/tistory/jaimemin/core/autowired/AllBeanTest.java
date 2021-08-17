package com.tistory.jaimemin.core.autowired;

import com.tistory.jaimemin.core.config.AutoAppConfig;
import com.tistory.jaimemin.core.discount.DiscountPolicy;
import com.tistory.jaimemin.core.member.entity.Grade;
import com.tistory.jaimemin.core.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AllBeanTest {
    
    @Test
    void findAllBean() {
        ApplicationContext ac
                = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = Member.builder()
                .id(1L)
                .name("userA")
                .grade(Grade.VIP)
                .build();
        int discountPrice
                = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice
                = discountService.discount(member, 20000, "rateDiscountPolicy");

        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    // DIP를 유지하며 동적으로 빈 주입
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;
        
        public DiscountService(Map<String, DiscountPolicy> policyMap
                , List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            return discountPolicy.discount(member, price);
        }
    }
}
