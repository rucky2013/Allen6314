package com.allenway.serviceImpl;

import com.allenway.dao.TagDao;
import com.allenway.service.TagService;
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
}
