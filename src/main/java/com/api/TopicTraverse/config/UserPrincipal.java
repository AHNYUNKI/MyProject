package com.api.TopicTraverse.config;

import com.api.TopicTraverse.domain.user.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class UserPrincipal extends org.springframework.security.core.userdetails.User {

    private final Long userId;

    public UserPrincipal(User user) {
        super(user.getEmail(), user.getPassword(), List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        ));
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }

}
