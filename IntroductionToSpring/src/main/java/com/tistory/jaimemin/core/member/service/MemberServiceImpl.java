package com.tistory.jaimemin.core.member.service;

import com.tistory.jaimemin.core.member.entity.Member;
import com.tistory.jaimemin.core.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
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

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
