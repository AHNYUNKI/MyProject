package com.api.TopicTraverse.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.*;


@Getter @Setter
@Builder
public class PostPage {

    private static final int MAX_SIZE = 1000;

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 10;

    public long getOffset() {
        return (long) (max(1, page) - 1) * min(size, MAX_SIZE);
    }



}
