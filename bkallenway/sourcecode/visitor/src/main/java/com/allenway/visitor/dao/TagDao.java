package com.allenway.visitor.dao;

import com.allenway.visitor.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by wuhuachuan on 16/4/2.
 */
public interface TagDao  extends JpaRepository<Tag, String> {

    @Query("select tag from Tag tag where isDelete=false")
    List<Tag> findAllTags();

    @Query("select tag from Tag tag where isDelete=false and id = :id")
    Tag findTagById(@Param(value = "id") String tagId);
}
