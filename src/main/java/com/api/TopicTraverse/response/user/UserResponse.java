package com.api.TopicTraverse.response.user;

import com.api.TopicTraverse.domain.post.Post;
import com.api.TopicTraverse.domain.user.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserResponse {
    private final String name;

    private final String email;

    private final Role role;

    private List<Post> posts = new ArrayList<>();

    @Builder
    public UserResponse(String name, String email, Role role, List<Post> posts) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.posts = posts;
    }
}
