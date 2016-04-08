package controller;

import com.allenway.visitor.controller.CommentController;
import com.allenway.visitor.entity.Article;
import com.allenway.visitor.service.ArticleService;
import com.allenway.visitor.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by wuhuachuan on 16/4/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class CommentControllerTest {

    @Autowired
    private CommentController commentController;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @Before
    public void init(){
        commentController.setCommentService(commentService);
        commentController.setArticleService(articleService);
    }

    @Test
    public void addComment() throws Exception {

    }
}