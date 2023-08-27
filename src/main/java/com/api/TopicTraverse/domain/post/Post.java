package com.api.TopicTraverse.domain.post;

import com.api.TopicTraverse.domain.BaseTime;
import com.api.TopicTraverse.request.PostEdit;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private int hit;

    @Builder
    public Post(Long id, String title, String content, int hit) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
    }

    public void hitPlus() {
        int view = 1;

        hit += view;


    }


    public void edit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
