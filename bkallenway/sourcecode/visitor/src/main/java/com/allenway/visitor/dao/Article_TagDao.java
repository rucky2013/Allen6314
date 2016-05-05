package com.allenway.visitor.dao;

import com.allenway.visitor.entity.Article_Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/8.
 */
public interface Article_TagDao extends JpaRepository<Article_Tag, String> {

    @Query("update Article_Tag set isDelete=true where articleId=:id")
    @Modifying
    @Transactional
    void deleteByArticleId(@Param(value = "id") String id);

    @Query("select article_Tag from Article_Tag article_Tag where isDelete=false and articleId=:id")
    List<Article_Tag> findByArticleId(@Param(value = "id") String id);
}
