package com.allenway.visitor.controller;

import com.allenway.commons.exception.DataNotFoundException;
import com.allenway.utils.response.ReturnTemplate;
import com.allenway.visitor.entity.Article;
import com.allenway.visitor.entity.Tag;
import com.allenway.visitor.service.ArticleService;
import com.allenway.utils.validparam.ValidUtils;
import com.allenway.visitor.service.TagService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhuachuan on 16/3/9.
 */

@Data
@Slf4j
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    /**
     * 添加文章(包括新增的和修改的)
     * @param article
     * @return
     */
    @RequestMapping(value = "/save-article",method = RequestMethod.POST)
    public Object saveArticle(Article article, HttpServletRequest request){

        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(validArticleParam(article,request)){

            ArrayList<Tag> tags = new ArrayList<Tag>();
            tags.add(tagService.findTagById(request.getParameter("tagId")));
            article.setTags(tags);

            articleService.save(article);
        } else {
            throw new IllegalArgumentException("param is invalid!");
        }
        return returnTemplate;
    }

    /**
     * 验证添加的文章的参数合理性
     * @param article
     * @return
     */
    private boolean validArticleParam(Article article,HttpServletRequest request) {

        log.info("validArticleParam function ... article = {}, tagId = {}.",article,request.getParameter("tagId"));

        if(StringUtils.isEmpty(article.getTitle()) ||
                StringUtils.isEmpty(article.getContent()) ||
                StringUtils.isEmpty(article.getClassifyId()) ||
                request.getParameter("tagId").isEmpty()){
            return false;
        } else {
            return true;
        }
    }


    /**
     * 删除 文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete-article-by-id",method = RequestMethod.POST)
    public Object deleteArticleById(@RequestParam String id){

        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(ValidUtils.validIdParam(id)){
            Article article = articleService.findArticleById(id);

            if(article == null){
                throw new DataNotFoundException("article == null");
            } else {
                articleService.delete(article);
            }
        } else {
            throw new IllegalArgumentException("Param is invalid!");
        }
        return returnTemplate;
    }

    /**
     * 根据 Id 查找某一篇文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/find-article-by-id",method = RequestMethod.GET)
    public Object findArticleById(String id) {

        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(ValidUtils.validIdParam(id)){
            Article article = articleService.findArticleById(id);

            if(article == null){
                throw new DataNotFoundException("article == null");
            } else {
                returnTemplate.addData("article",article);
            }
        } else {
            throw new IllegalArgumentException("Param is invalid!");
        }
        return returnTemplate;
    }

    /**
     * 获取全部的文章
     * @return
     */
    @RequestMapping(value = "/get-all-articles",method = RequestMethod.GET)
    public Object getAllArticles(){
        ReturnTemplate returnTemplate = new ReturnTemplate();
        returnTemplate.addData("articles",articleService.findAllArticles());
        return  returnTemplate;
    }

}
