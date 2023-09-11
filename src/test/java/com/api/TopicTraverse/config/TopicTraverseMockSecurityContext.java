package com.api.TopicTraverse.config;


import com.api.TopicTraverse.domain.user.User;
import com.api.TopicTraverse.repository.member.UserRepnsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

@RequiredArgsConstructor
public class TopicTraverseMockSecurityContext implements WithSecurityContextFactory<TopicTraversMocUser> {

    private final UserRepnsitory userRepnsitory;


    @Override
    public SecurityContext createSecurityContext(TopicTraversMocUser annotation) {
        User user = User.builder()
                .email(annotation.email())
                .name(annotation.name())
                .password(annotation.password())
                .role(annotation.role())
                .build();

        userRepnsitory.save(user);

        UserPrincipal userPrincipal = new UserPrincipal(user);

        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_USER");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal,
                user.getPassword(),
                List.of(role)
                );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticationToken);

        return context;
    }
}
