package com.tistory.jaimemin.core.order;

import com.tistory.jaimemin.core.member.entity.Grade;
import com.tistory.jaimemin.core.member.entity.Member;
import com.tistory.jaimemin.core.member.service.MemberService;
import com.tistory.jaimemin.core.member.service.MemberServiceImpl;
import com.tistory.jaimemin.core.order.entity.Order;
import com.tistory.jaimemin.core.order.service.OrderService;
import com.tistory.jaimemin.core.order.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = Member.builder()
                .id(1L)
                .name("memberA")
                .grade(Grade.VIP)
                .build();
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
