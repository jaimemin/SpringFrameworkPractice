package com.tistory.jaimemin.core.discount;

import com.tistory.jaimemin.core.member.entity.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
