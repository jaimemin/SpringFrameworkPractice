package com.tistory.jaimemin.core.discount;

import com.tistory.jaimemin.core.annotation.MainDiscountPolicy;
import com.tistory.jaimemin.core.member.entity.Grade;
import com.tistory.jaimemin.core.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }

        return 0;
    }
}
