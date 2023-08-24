package com.api.TopicTraverse.repository.post;

import com.api.TopicTraverse.domain.post.Post;
import com.api.TopicTraverse.domain.post.QPost;
import com.api.TopicTraverse.request.PostPage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostPage postPage) {

        QPost post = QPost.post;

        return jpaQueryFactory.selectFrom(post)
                .limit(postPage.getSize())
                .offset(postPage.getOffset())
                .orderBy(post.id.desc())
                .fetch();
    }

}
