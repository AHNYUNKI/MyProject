package com.api.TopicTraverse.service.user;

import com.api.TopicTraverse.domain.user.Role;
import com.api.TopicTraverse.domain.user.User;
import com.api.TopicTraverse.repository.member.UserRepnsitory;
import com.api.TopicTraverse.request.user.UserCreate;
import com.api.TopicTraverse.request.user.UserEditor;
import com.api.TopicTraverse.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepnsitory userRepnsitory;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(UserCreate userCreate) {

        User user = User.builder()
                .email(userCreate.getEmail())
                .password(passwordEncoder.encode(userCreate.getPassword()))
                .name(userCreate.getName())
                .role(Role.USER)
                .build();

        userRepnsitory.save(user);
    }

    public UserResponse getUser(Long id) {
        User user = userRepnsitory.findById(id).orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .posts(user.getPosts())
                .build();

    }

    @Transactional
    public void userEdit(Long id, UserEditor userEditor) {
        User user = userRepnsitory.findById(id).orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

        user.edit(userEditor.getName(), passwordEncoder.encode(userEditor.getPassword()));
    }

    public void delete(Long id) {
        User user = userRepnsitory.findById(id).orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

        userRepnsitory.delete(user);
    }

}
