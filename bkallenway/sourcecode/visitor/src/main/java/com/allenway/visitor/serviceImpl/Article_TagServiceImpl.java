package com.allenway.visitor.serviceImpl;

import com.allenway.visitor.dao.Article_TagDao;
import com.allenway.visitor.entity.Article_Tag;
import com.allenway.visitor.service.Article_TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/8.
 */

@Service
public class Article_TagServiceImpl implements Article_TagService {

    @Autowired
    private Article_TagDao article_tagDao;

    @Override
    public void save(Article_Tag article_tag) {
        article_tagDao.save(article_tag);
    }

    @Override
    public void deleteByArticleId(String id) {
        article_tagDao.deleteByArticleId(id);
    }

    @Override
    public List<Article_Tag> findByArticleId(String id) {
        return article_tagDao.findByArticleId(id);
    }
}
