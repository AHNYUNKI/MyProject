package com.api.TopicTraverse.repository.post;

import com.api.TopicTraverse.domain.post.Post;
import com.api.TopicTraverse.request.PostPage;
import com.api.TopicTraverse.response.post.PostGet;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
