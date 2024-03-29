package com.api.TopicTraverse.domain.post;

import com.api.TopicTraverse.domain.BaseTime;
import com.api.TopicTraverse.domain.user.User;
import com.api.TopicTraverse.request.post.PostEditor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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


    public void edit(PostEditor postEditor) {
        this.title = postEditor.getTitle() != null ? postEditor.getTitle() : title;
        this.content = postEditor.getContent() != null ? postEditor.getContent() : content;
    }
}
