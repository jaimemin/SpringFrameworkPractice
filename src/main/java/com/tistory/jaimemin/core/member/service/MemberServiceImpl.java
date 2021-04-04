package com.tistory.jaimemin.core.member.service;

import com.tistory.jaimemin.core.member.entity.Member;
import com.tistory.jaimemin.core.member.repository.MemberRepository;
import com.tistory.jaimemin.core.member.repository.MemoryMemberRepository;
import org.springframework.util.ObjectUtils;

public class MemberServiceImpl implements MemberService {

    // SOLID 원칙 중 DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
