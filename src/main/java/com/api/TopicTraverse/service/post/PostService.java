package com.api.TopicTraverse.service.post;

import com.api.TopicTraverse.domain.post.Post;
import com.api.TopicTraverse.domain.user.User;
import com.api.TopicTraverse.exception.PostNotFound;
import com.api.TopicTraverse.repository.member.UserRepnsitory;
import com.api.TopicTraverse.repository.post.PostRepository;
import com.api.TopicTraverse.request.post.PostEditor;
import com.api.TopicTraverse.request.post.PostPage;
import com.api.TopicTraverse.request.post.PostWrite;
import com.api.TopicTraverse.response.post.PostGet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepnsitory userRepnsitory;

    @Transactional
    public void postCreate(Long userId ,PostWrite postWrite) {
        User user = userRepnsitory.findById(userId).orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));

        Post post = Post.builder()
                .title(postWrite.getTitle())
                .content(postWrite.getContent())
                .build();

        postRepository.save(post);

    }

    public PostGet postGet(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        post.hitPlus();

        postRepository.save(post);

        return PostGet.toGet(post);

    }

    public List<PostGet> getList(PostPage postPage) {


        return postRepository.getList(postPage).stream()
                .map(PostGet::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void postEdit(Long postId, PostEditor postEditor) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        post.edit(postEditor);

    }

    public void postDelete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }

}
