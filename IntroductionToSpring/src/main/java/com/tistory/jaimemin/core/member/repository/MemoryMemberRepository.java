package com.tistory.jaimemin.core.member.repository;

import com.tistory.jaimemin.core.member.entity.Member;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    // 예제이기 때문에 HashMap
    // 사실 동시성 문제가 발생할 수 있어 ConcurrentHashMap을 사용해야 함
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        if(ObjectUtils.isEmpty(member)) {
            return;
        }

        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        if(ObjectUtils.isEmpty(memberId)) {
            return null;
        }

        return store.get(memberId);
    }
}
