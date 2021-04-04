package com.tistory.jaimemin.core.member;

import com.tistory.jaimemin.core.member.entity.Grade;
import com.tistory.jaimemin.core.member.entity.Member;
import com.tistory.jaimemin.core.member.service.MemberService;
import com.tistory.jaimemin.core.member.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member foundMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(foundMember);
    }
}
