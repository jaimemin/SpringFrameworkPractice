package com.tistory.jaimemin.core.member.repository;

import com.tistory.jaimemin.core.member.entity.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
