package com.allenway.visitor.dao;

import com.allenway.visitor.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/8.
 */
public interface CommentDao extends JpaRepository<Comment, String> {

    @Query("update Comment set isDelete=true where articleId=:id")
    @Modifying
    @Transactional
    void deleteByArticleId(@Param(value = "id") String id);

    @Query("select comment from Comment comment where isDelete=false and articleId=:id")
    List<Comment> findByArticleId(@Param(value = "id") String id);
}
