package com.allenway.visitor.service;

import com.allenway.visitor.entity.Article_Tag;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/8.
 */
public interface Article_TagService {
    void save(Article_Tag article_tag);

    void deleteByArticleId(String id);

    List<Article_Tag> findByArticleId(String id);
}
