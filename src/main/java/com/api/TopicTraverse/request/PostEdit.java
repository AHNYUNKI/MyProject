package com.api.TopicTraverse.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostEdit {

    private final String title;

    private final String content;

    @Builder
    public PostEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
