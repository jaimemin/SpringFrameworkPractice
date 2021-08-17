package com.tistory.jaimemin.core.beanfind;

import com.tistory.jaimemin.core.config.AppConfig;
import com.tistory.jaimemin.core.member.service.MemberService;

import com.tistory.jaimemin.core.member.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("Find by Bean's Name")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Find by Bean's Type")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 구현체에 의존하면 안되므로 좋은 코드는 아님
    @Test
    @DisplayName("Find by Specific Type")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Can't find by Bean's Name")
    void findBeanByNameX() {
        assertThrows(NoSuchBeanDefinitionException.class
                , () -> ac.getBean("xxxxx", MemberService.class));
    }
}
