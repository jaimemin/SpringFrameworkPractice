package com.tistory.jaimemin.core.member.service;

import com.tistory.jaimemin.core.member.entity.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
