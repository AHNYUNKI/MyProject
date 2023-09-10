package com.api.TopicTraverse.request.member;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberSave {

    @NotBlank(message = "이메일은 필수입니다.")
    private final String email;

    @NotBlank(message = "패스워드는 필수입니다.")
    private final String password;

    @NotBlank(message = "이름은 필수입니다.")
    private final String name;

    @Builder
    public MemberSave(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
