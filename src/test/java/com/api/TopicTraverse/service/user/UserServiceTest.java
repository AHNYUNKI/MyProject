package com.api.TopicTraverse.service.user;

import com.api.TopicTraverse.domain.user.Role;
import com.api.TopicTraverse.domain.user.User;
import com.api.TopicTraverse.repository.member.UserRepnsitory;
import com.api.TopicTraverse.request.user.UserCreate;
import com.api.TopicTraverse.request.user.UserEditor;
import com.api.TopicTraverse.response.user.UserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepnsitory userRepnsitory;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterEach
    void clean() {
        userRepnsitory.deleteAll();
    }

    @Test
    @DisplayName("회원가입")
    public void test1() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("userA@gmail.com")
                .password("1111")
                .name("userA")
                .build();

        // when
        userService.save(userCreate);

        // then
        User user = userRepnsitory.findAll().get(0);
        assertEquals("userA@gmail.com", user.getEmail());
        assertEquals("userA", user.getName());
        assertNotNull(user.getPassword());
    }

    @Test
    @DisplayName("유저가 본인 정보를 요청한다.")
    public void test2() {
        // given
        User userCreate = User.builder()
                .email("userA@gmail.com")
                .password("1111")
                .role(Role.USER)
                .name("userA")
                .build();

        userRepnsitory.save(userCreate);

        // when
        UserResponse user = userService.getUser(userCreate.getId());

        // then
        assertEquals("userA@gmail.com", user.getEmail());
        assertEquals("userA", user.getName());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    @DisplayName("유저 개인정보 수정")
    public void test3() {
        // given
        User user = User.builder()
                .name("userA")
                .password(passwordEncoder.encode("1111"))
                .email("userA@gmail.com")
                .role(Role.USER)
                .build();

        userRepnsitory.save(user);

        UserEditor userEditor = UserEditor.builder()
                .name("userA_수정")
                .password("2222")
                .build();
        // when
        userService.userEdit(user.getId(), userEditor);

        User Response = userRepnsitory.findById(user.getId()).get();

        // then
        assertEquals("userA_수정", Response.getName());
    }

    @Test
    @DisplayName("회원 삭제")
    public void test4() {
        // given
        User user = User.builder()
                .name("userA")
                .password(passwordEncoder.encode("1111"))
                .email("userA@gmail.com")
                .role(Role.USER)
                .build();

        userRepnsitory.save(user);

        // when
        userService.delete(user.getId());

        // then
        assertEquals(0, userRepnsitory.count());
    }

}