package com.allenway.visitor.controller;

import com.allenway.infrustructure.exception.DataNotFoundException;
import com.allenway.utils.response.ReturnTemplate;
import com.allenway.utils.validparam.ValidUtils;
import com.allenway.visitor.entity.Article;
import com.allenway.visitor.entity.Comment;
import com.allenway.visitor.service.ArticleService;
import com.allenway.visitor.service.CommentService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuhuachuan on 16/4/8.
 */

@Slf4j
@Data
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    /**
     * 保存新增的评论
     * @param comment
     * @param request
     * @return
     */
    @RequestMapping(value = "/comment/add-comment",method = RequestMethod.POST)
    public Object addComment(Comment comment, HttpServletRequest request){
        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(validData(comment,request)){

            Article article = articleService.findArticleById(request.getParameter("id"));
            if(article == null){
                throw new DataNotFoundException();
            } else {
                comment.setArticleId(article.getId());
            }
            commentService.save(comment);
        } else {
            throw new IllegalArgumentException();
        }

        return returnTemplate;
    }

    /**
     * 验证新增的评论参数是否合理
     * @param comment
     * @param request
     * @return
     */
    private boolean validData(Comment comment, HttpServletRequest request) {
        if(ValidUtils.validIdParam(comment.getName()) &&
                ValidUtils.validIdParam(comment.getEmail()) &&
                ValidUtils.validIdParam(comment.getContent()) &&
                ValidUtils.validIdParam(request.getParameter("id"))) {
            return true;
        } else {
            return false;
        }
    }
}
