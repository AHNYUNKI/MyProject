package com.api.TopicTraverse.config;

import com.api.TopicTraverse.domain.user.Role;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = TopicTraverseMockSecurityContext.class)
public @interface TopicTraversMocUser {

    String name() default "userA";

    String email() default "userA@gmail.com";

    String password() default "1111";

    Role role() default Role.USER;

}
