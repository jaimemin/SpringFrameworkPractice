package com.tistory.jaimemin.core.member.service;

import com.tistory.jaimemin.core.member.entity.Member;
import com.tistory.jaimemin.core.member.repository.MemberRepository;
import org.springframework.util.ObjectUtils;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /*
    // SOLID 원칙 중 DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    */

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        if(ObjectUtils.isEmpty(member)) {
            return;
        }

        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        if(ObjectUtils.isEmpty(memberId)) {
            return null;
        }

        return memberRepository.findById(memberId);
    }
}
