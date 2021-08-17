package com.tistory.jaimemin.core.discount;

import com.tistory.jaimemin.core.member.entity.Grade;
import com.tistory.jaimemin.core.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_ok() {
        // given
        Member member = Member.builder()
                .id(1L)
                .name("memberVIP")
                .grade(Grade.VIP)
                .build();

        // when
        int discount = rateDiscountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_no() {
        // given
        Member member = Member.builder()
                .id(1L)
                .name("memberVIP")
                .grade(Grade.BASIC)
                .build();

        // when
        int discount = rateDiscountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0);
    }
}