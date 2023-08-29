package com.api.TopicTraverse.controller.post;

import com.api.TopicTraverse.domain.post.Post;
import com.api.TopicTraverse.repository.post.PostRepository;
import com.api.TopicTraverse.request.PostEdit;
import com.api.TopicTraverse.request.PostPage;
import com.api.TopicTraverse.request.PostWrite;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("Controller -> POST 보내기")
    public void test1() throws Exception {
        // given
        PostWrite postWrite = PostWrite.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(postWrite);

        // expect
        mockMvc.perform(post("/post")
                .contentType(APPLICATION_JSON)
                .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("게시글 1개 조회")
    public void test2 () throws Exception {
        // given
        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        postRepository.save(post);

        // expect
        mockMvc.perform(get("/post/{postId}", post.getId())
                .contentType(APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("제목입니다."))
                .andExpect(jsonPath("$.content").value("내용입니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("제목을 작성하지 않으면 에러가 발생한다.")
    public void test3() throws Exception {
        // given
        PostWrite postWrite = PostWrite.builder()
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(postWrite);

        // expect
        mockMvc.perform(post("/post")
                .contentType(APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.").value("400"))
//                .andExpect(jsonPath("$.").value("잘못된 요청입니다."))
//                .andExpect(jsonPath("$.validation.title").value("제목은 필수 입니다."))
                .andDo(print());
    }
    
    @Test
    @DisplayName("페이지 체크")
    public void test4() throws Exception {
        // given
        List<Post> posts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("제목" + i)
                        .content("내용" + i)
                        .build()).collect(Collectors.toList());

        postRepository.saveAll(posts);

        PostPage postPage = PostPage.builder()
                .page(0)
                .build();


        // expect
        mockMvc.perform(get("/posts?page=0&size=10")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(10)))
                .andExpect(jsonPath("$.[0].title").value("제목19"))
                .andExpect(jsonPath("$.[0].content").value("내용19"))
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 수정")
    public void test5() throws Exception {
        // given
        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목_수정입니다.")
                .content("내용_수정입니다.")
                .build();

        // expect
        mockMvc.perform(patch("/post/{postId}",post.getId())
                .contentType(APPLICATION_JSON)
                .content((objectMapper.writeValueAsString(postEdit)))
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 삭제")
    public void test6() throws Exception {
        // given
        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        postRepository.save(post);

        // expect
        mockMvc.perform(delete("/post/{postId}",post.getId())
                .contentType(APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }
}