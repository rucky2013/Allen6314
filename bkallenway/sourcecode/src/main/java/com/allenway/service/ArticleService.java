package com.allenway.service;

import com.allenway.entity.Article;

/**
 * Created by wuhuachuan on 16/3/9.
 */
public interface ArticleService {
    void save(Article article);

    Article findArticleById(String id);

    void delete(Article article);
}
