package com.allenway.dao;

import com.allenway.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by wuhuachuan on 16/3/9.
 */
public interface ArticleDao extends JpaRepository<Article, String> {

    @Query("select article from Article article where isDelete=false and id=:id")
    Article findArticleById(@Param(value = "id")String id);
}
