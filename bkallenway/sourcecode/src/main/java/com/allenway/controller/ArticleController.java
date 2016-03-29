package com.allenway.controller;

import com.allenway.entity.Article;
import com.allenway.infrustructure.DataNotFoundException;
import com.allenway.service.ArticleService;
import com.allenway.utils.ReturnTemplate;
import com.allenway.utils.ValidUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 添加文章(包括新增的和修改的)
     * @param article
     * @return
     */
    @RequestMapping(value = "/save-article",method = RequestMethod.POST)
    public Object saveArticle(@RequestParam Article article){

        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(validArticleParam(article)){
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
    private boolean validArticleParam(Article article) {
        if(StringUtils.isEmpty(article.getTitle()) ||
                StringUtils.isEmpty(article.getContent()) ||
                StringUtils.isEmpty(article.getClassifyId())){
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
    @RequestMapping(value = "/find-article-by-id",method = RequestMethod.POST)
    public Object findArticleById(@RequestParam String id) {

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
}
