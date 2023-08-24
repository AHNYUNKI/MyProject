package com.api.TopicTraverse.controller.post;

import com.api.TopicTraverse.request.PostEdit;
import com.api.TopicTraverse.request.PostPage;
import com.api.TopicTraverse.request.PostWrite;
import com.api.TopicTraverse.response.post.PostGet;
import com.api.TopicTraverse.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public void write(@RequestBody @Valid PostWrite postWrite) {


        postService.postCreate(postWrite);

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
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit postEdit) {
        postService.postEdit(postId, postEdit);
    }

    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.postDelete(postId);
    }

}
