package com.allenway.visitor.service;

import com.allenway.visitor.entity.Comment;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/8.
 */
public interface CommentService {
    void save(Comment comment);

    void deleteByArticleId(String id);

    List<Comment> findByArticleId(String id);
}
