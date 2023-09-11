package com.api.TopicTraverse.service.post;

import com.api.TopicTraverse.domain.post.Post;
import com.api.TopicTraverse.domain.user.Role;
import com.api.TopicTraverse.domain.user.User;
import com.api.TopicTraverse.repository.member.UserRepnsitory;
import com.api.TopicTraverse.repository.post.PostRepository;
import com.api.TopicTraverse.request.post.PostEditor;
import com.api.TopicTraverse.request.post.PostPage;
import com.api.TopicTraverse.request.post.PostWrite;
import com.api.TopicTraverse.response.post.PostGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepnsitory userRepnsitory;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
        userRepnsitory.deleteAll();
    }

    @Test
    @DisplayName("게시글 작성")
    public void test1() {
        // given
        User user = User.builder()
                .name("userA")
                .password("1111")
                .email("userA@gmail.com")
                .role(Role.USER)
                .build();

        userRepnsitory.save(user);

        PostWrite postWrite = PostWrite.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // when
        postService.postCreate(user.getId(), postWrite);


        // then
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1건 조회")
    public void test2() {
        // given
        Post postRequest = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        postRepository.save(postRequest);
        // when
        PostGet postGet = postService.postGet(postRequest.getId());

        // then
        assertNotNull(postGet);
        assertEquals(1L, postRepository.count());
        assertEquals("제목입니다.", postGet.getTitle());
        assertEquals("내용입니다.", postGet.getContent());
        assertEquals(1, postGet.getHit());

    }

    @Test
    @DisplayName("글 페이징 조회")
    public void test3() {
        // given
        List<Post> postRequest = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("제목"+ i)
                        .content("내용"+ i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(postRequest);

        PostPage postPage = PostPage.builder()
                .page(0)
                .build();
        // when
        List<PostGet> posts = postService.getList(postPage);

        // then
        assertEquals(10L, posts.size());
        assertEquals("제목19", posts.get(0).getTitle());
    }

    @Test
    @DisplayName("게시글 수정")
    public void test4() {
        // given
        Post postRequest = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        postRepository.save(postRequest);

        PostEditor postEditor = PostEditor.builder()
                .title("제목_수정입니다.")
                .content("내용_수정입니다.")
                .build();

        // when
        postService.postEdit(postRequest.getId(), postEditor);

        // then
        Post post = postRepository.findAll().get(0);
        assertEquals("제목_수정입니다.", post.getTitle());
        assertEquals("내용_수정입니다.", post.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    public void test5() {
        // given
        Post postRequest = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        Post post = postRepository.save(postRequest);

        // when
        postService.postDelete(postRequest.getId());

        // then
        assertEquals(0, postRepository.count());
    }

}