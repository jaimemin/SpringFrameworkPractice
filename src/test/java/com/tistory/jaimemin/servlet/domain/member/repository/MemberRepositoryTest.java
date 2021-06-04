package com.tistory.jaimemin.servlet.domain.member.repository;

import com.tistory.jaimemin.servlet.domain.member.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = Member.builder()
                .username("hello")
                .age(20)
                .build();

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member foundMember = memberRepository.findById(savedMember.getId());

        assertThat(foundMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        // given
        Member member1 = Member.builder()
                .username("member1")
                .age(20)
                .build();
        Member member2 = Member.builder()
                .username("member2")
                .age(30)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}