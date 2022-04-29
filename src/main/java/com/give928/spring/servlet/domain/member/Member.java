package com.give928.spring.servlet.domain.member;

import lombok.*;

@Getter
@Setter
@ToString
public class Member {
    private Long id;
    private String username;
    private int age;

    @Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
