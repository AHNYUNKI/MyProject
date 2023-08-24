package com.api.TopicTraverse.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostWrite {

    @NotBlank(message = "제목은 필수 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입니다.")
    private String content;

    @Builder
    public PostWrite(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
