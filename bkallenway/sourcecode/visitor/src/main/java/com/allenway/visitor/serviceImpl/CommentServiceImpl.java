package com.allenway.visitor.serviceImpl;

import com.allenway.visitor.dao.CommentDao;
import com.allenway.visitor.entity.Comment;
import com.allenway.visitor.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/8.
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void deleteByArticleId(String id) {
        commentDao.deleteByArticleId(id);
    }

    @Override
    public List<Comment> findByArticleId(String id) {
        return commentDao.findByArticleId(id);
    }
}
