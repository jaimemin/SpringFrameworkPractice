package com.tistory.jaimemin.core.order;

import com.tistory.jaimemin.core.discount.FixDiscountPolicy;
import com.tistory.jaimemin.core.member.entity.Grade;
import com.tistory.jaimemin.core.member.entity.Member;
import com.tistory.jaimemin.core.member.repository.MemoryMemberRepository;
import com.tistory.jaimemin.core.order.entity.Order;
import com.tistory.jaimemin.core.order.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService
                = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order
                = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
