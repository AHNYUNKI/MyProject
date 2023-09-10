package com.api.TopicTraverse.controller.post;

import com.api.TopicTraverse.request.member.MemberSave;
import com.api.TopicTraverse.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private MemberService memberService;

    @PostMapping("/save")
    public void save(@RequestBody @Valid MemberSave memberSave) {
        memberService.save(memberSave);
    }

}
