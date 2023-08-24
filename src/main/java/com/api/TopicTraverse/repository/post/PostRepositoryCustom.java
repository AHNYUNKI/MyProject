package com.api.TopicTraverse.repository.post;

import com.api.TopicTraverse.domain.post.Post;
import com.api.TopicTraverse.request.PostPage;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostPage postPage);
}
