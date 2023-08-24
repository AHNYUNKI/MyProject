package com.api.TopicTraverse.response.post;

import com.api.TopicTraverse.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class PostGet {

    private final Long id;

    private final String title;

    private final String content;

    private final int hit;

    private final LocalDateTime createTime;

    private final LocalDateTime updateTime;

    @Builder
    public PostGet(Long id, String title, String content, int hit, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public PostGet(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.hit = post.getHit();
        this.createTime = post.getCreateTime();
        this.updateTime = post.getUpdateTime();
    }

    public static PostGet toGet(Post post) {
        return PostGet.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .hit(post.getHit())
                .createTime(post.getCreateTime())
                .updateTime(post.getUpdateTime())
                .build();
    }

}
