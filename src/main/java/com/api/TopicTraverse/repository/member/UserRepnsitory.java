package com.api.TopicTraverse.repository.member;

import com.api.TopicTraverse.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepnsitory extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}
