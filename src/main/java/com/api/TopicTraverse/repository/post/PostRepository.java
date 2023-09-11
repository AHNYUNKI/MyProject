package com.api.TopicTraverse.repository.post;

import com.api.TopicTraverse.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
