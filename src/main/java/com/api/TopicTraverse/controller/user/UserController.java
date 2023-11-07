package com.api.TopicTraverse.controller.user;

import com.api.TopicTraverse.config.UserPrincipal;
import com.api.TopicTraverse.request.user.UserCreate;
import com.api.TopicTraverse.request.user.UserEditor;
import com.api.TopicTraverse.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/save")
    public void save(@RequestBody @Valid UserCreate userCreate) {
        userService.save(userCreate);
    }

    @PatchMapping("/edit")
    public void edit(@AuthenticationPrincipal UserPrincipal userPrincipal, UserEditor userEditor) {
        userService.userEdit(userPrincipal.getUserId(), userEditor);
    }

    @DeleteMapping("/delete")
    public void delete(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.delete(userPrincipal.getUserId());
    }
}
