package com.give928.spring.servlet.web.frontcontroller.v3.controller;

import com.give928.spring.servlet.domain.member.Member;
import com.give928.spring.servlet.domain.member.MemberRepository;
import com.give928.spring.servlet.web.frontcontroller.ModelView;
import com.give928.spring.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = Member.builder().username(username).age(age).build();
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
