package com.give928.spring.servlet.web.frontcontroller.v4.controller;

import com.give928.spring.servlet.domain.member.Member;
import com.give928.spring.servlet.domain.member.MemberRepository;
import com.give928.spring.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = Member.builder().username(username).age(age).build();
        memberRepository.save(member);

        model.put("member", member);

        return "save-result";
    }
}
