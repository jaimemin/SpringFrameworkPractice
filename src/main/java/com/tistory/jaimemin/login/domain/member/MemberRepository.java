package com.tistory.jaimemin.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> memberStorage = new HashMap<>();

    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);

        log.info("save: member = {}", member);

        memberStorage.put(member.getId(), member);

        return member;
    }

    public Member findById(Long id) {
        return memberStorage.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findFirst();

//        List<Member> members = findAll();
//
//        for (Member member : members) {
//            if (member.getLoginId().equals(loginId)) {
//                return Optional.of(member);
//            }
//        }
//
//        return Optional.empty();
    }

    public List<Member> findAll() {
        return new ArrayList<>(memberStorage.values());
    }

    public void clearStorage() {
        memberStorage.clear();
    }
}
