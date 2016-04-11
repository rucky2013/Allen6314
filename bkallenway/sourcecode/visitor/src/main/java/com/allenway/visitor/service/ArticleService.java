package com.allenway.visitor.service;


import com.allenway.visitor.entity.Article;

import java.util.List;

/**
 * Created by wuhuachuan on 16/3/9.
 */
public interface ArticleService {
    Article save(Article article);

    Article findArticleById(String id);

    void delete(Article article);

    List<Article> findArticlesByClassifyId(String id);

    List<Article> findAllArticles();

    //找到推荐的文章
    List<Article> findRecommendArticles();
}
