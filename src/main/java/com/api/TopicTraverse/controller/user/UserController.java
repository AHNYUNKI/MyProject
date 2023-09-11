package com.api.TopicTraverse.controller.user;

import com.api.TopicTraverse.request.user.UserCreate;
import com.api.TopicTraverse.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/save")
    public void save(@RequestBody @Valid UserCreate userCreate) {
        userService.save(userCreate);
    }

}
