package com.api.TopicTraverse.service.post;

import com.api.TopicTraverse.domain.post.Post;
import com.api.TopicTraverse.exception.PostException;
import com.api.TopicTraverse.exception.PostNotFound;
import com.api.TopicTraverse.repository.post.PostRepository;
import com.api.TopicTraverse.request.PostEdit;
import com.api.TopicTraverse.request.PostPage;
import com.api.TopicTraverse.request.PostWrite;
import com.api.TopicTraverse.response.post.PostGet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void postCreate(PostWrite postWrite) {

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
    public void postEdit(Long postId, PostEdit postEdit) {
        Post postGet = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        Post post = Post.builder()
                .id(postGet.getId())
                .title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        postRepository.save(post);
    }

    public void postDelete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }

}
