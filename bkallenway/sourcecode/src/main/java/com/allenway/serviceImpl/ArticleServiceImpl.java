package com.allenway.serviceImpl;

import com.allenway.dao.ArticleDao;
import com.allenway.entity.Article;
import com.allenway.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuhuachuan on 16/3/9.
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void save(Article article) {
        articleDao.saveAndFlush(article);
    }

    @Override
    public Article findArticleById(String id) {
        return articleDao.findArticleById(id);
    }

    @Override
    public void delete(Article article) {
        article.setIsDelete(true);
        articleDao.saveAndFlush(article);
    }
}
