package com.tistory.jaimemin.login.domain.login;

import com.tistory.jaimemin.login.domain.member.Member;
import com.tistory.jaimemin.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password) {
        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);

        return optionalMember
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);

//        Member member = optionalMember.get();
//
//        if (member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }
    }
}
