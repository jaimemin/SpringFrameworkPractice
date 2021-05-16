package com.tistory.jaimemin.core.order.service;

import com.tistory.jaimemin.core.discount.DiscountPolicy;
import com.tistory.jaimemin.core.member.entity.Member;
import com.tistory.jaimemin.core.member.repository.MemberRepository;
import com.tistory.jaimemin.core.order.entity.Order;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;

    /*
     * SOLID 원칙 중 DIP와 OCP 룰을 위반한 코드
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    */

    public OrderServiceImpl(MemberRepository memberRepository
            , DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        if (ObjectUtils.isEmpty(memberId) || StringUtils.isEmpty(itemName)) {
            return null;
        }

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return Order.builder()
                .memberId(memberId)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .discountPrice(discountPrice)
                .build();
    }

    // 싱글턴 적용이 되는지 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
