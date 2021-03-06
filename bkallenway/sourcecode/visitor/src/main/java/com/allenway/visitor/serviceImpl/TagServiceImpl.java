package com.allenway.visitor.serviceImpl;

import com.allenway.visitor.dao.TagDao;
import com.allenway.visitor.entity.Tag;
import com.allenway.visitor.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuhuachuan on 16/4/2.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagagDao;

    @Override
    public Object findAllTags() {
        return tagagDao.findAllTags();
    }

    @Override
    public Tag findTagById(String tagId) {
        return tagagDao.findTagById(tagId);
    }
}
