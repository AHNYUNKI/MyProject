package com.api.TopicTraverse.request.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEditor {

    private final String name;

    private final String password;

    @Builder
    public UserEditor(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
