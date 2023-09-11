package com.api.TopicTraverse.controller.post;

import com.api.TopicTraverse.config.UserPrincipal;
import com.api.TopicTraverse.request.post.PostEditor;
import com.api.TopicTraverse.request.post.PostPage;
import com.api.TopicTraverse.request.post.PostWrite;
import com.api.TopicTraverse.response.post.PostGet;
import com.api.TopicTraverse.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public void write(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid PostWrite postWrite) {


        postService.postCreate(userPrincipal.getUserId(), postWrite);

    }

    @GetMapping("/post/{postId}")
    public PostGet get(@PathVariable Long postId) {

        return postService.postGet(postId);

    }

    @GetMapping("/posts")
    public List<PostGet> getList(@ModelAttribute PostPage postPage) {

        return postService.getList(postPage);
    }

    @PatchMapping("/post/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEditor postEditor) {
        System.out.println("Received postId: " + postId);
        System.out.println("Received postEditor: " + postEditor);

        postService.postEdit(postId, postEditor);
    }

    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.postDelete(postId);
    }

}
