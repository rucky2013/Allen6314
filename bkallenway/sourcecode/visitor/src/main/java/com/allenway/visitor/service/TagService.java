package com.allenway.visitor.service;

import com.allenway.visitor.entity.Tag;

/**
 * Created by wuhuachuan on 16/4/2.
 */
public interface TagService {
    Object findAllTags();

    Tag findTagById(String tagId);
}
