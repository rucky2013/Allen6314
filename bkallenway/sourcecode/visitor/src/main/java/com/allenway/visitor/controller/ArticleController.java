package com.allenway.visitor.controller;

import com.allenway.infrustructure.exception.DataNotFoundException;
import com.allenway.utils.response.ReturnTemplate;
import com.allenway.utils.validparam.ValidUtils;
import com.allenway.visitor.entity.*;
import com.allenway.visitor.service.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wuhuachuan on 16/3/9.
 */

@Data
@Slf4j
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ClassifyService classifyService;

    @Autowired
    private TagService tagService;

    @Autowired
    private Article_TagService article_tagService;

    @Autowired
    private CommentService commentService;

    /**
     * 添加文章(包括新增的和修改的)
     * @param article
     * @return
     */
    @RequestMapping(value = "/auth/article/save-article",method = RequestMethod.POST)
    public Object saveArticle(Article article, HttpServletRequest request){

        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(validArticleParam(article,request)){

            Classify classify = classifyService.findClassifyById(request.getParameter("classifyId"));
            if(classify == null){
                throw new DataNotFoundException();
            } else {
                article.setClassifyId(classify.getId());
            }

            Tag tag = tagService.findTagById(request.getParameter("tagId"));
            if(tag == null){
                throw new DataNotFoundException();
            } else {

                //如果新增一篇文章,那么该分类的文章 + 1
                if(article.getId() == null){
                    classify.setArticleNum(classify.getArticleNum() + 1);
                    classifyService.save(classify);
                }

                Article art = articleService.save(article);
                article_tagService.save(new Article_Tag(art.getId(),tag.getId()));

            }
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
                StringUtils.isEmpty(request.getParameter("classifyId")) ||
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
    @RequestMapping(value = "/auth/article/delete-article-by-id",method = RequestMethod.POST)
    public Object deleteArticleById(@RequestParam String id){

        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(ValidUtils.validIdParam(id)){
            Article article = articleService.findArticleById(id);

            if(article == null){
                throw new DataNotFoundException("article == null");
            } else {
                //删除文章和 tag 的联系
                article_tagService.deleteByArticleId(article.getId());

                //删除文章和 classify 的联系
                //由于article 维护两者的关系,所以无需处理

                //删除文件和 comment 的联系
                commentService.deleteByArticleId(article.getId());

                //删除文章
                articleService.delete(article);

                //该分类下的文章 -1
                Classify classify = classifyService.findClassifyById(article.getClassifyId());
                classify.setArticleNum(classify.getArticleNum() - 1);
                classifyService.save(classify);
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
    @RequestMapping(value = "/article/find-article-by-id",method = RequestMethod.GET)
    public Object findArticleById(String id) {

        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(ValidUtils.validIdParam(id)){
            Article article = articleService.findArticleById(id);

            if(article == null){
                throw new DataNotFoundException("article == null");
            } else {
                setArticleTagClassifyComment(article);
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
    @RequestMapping(value = "/article/get-all-articles",method = RequestMethod.GET)
    public Object getAllArticles(){
        log.info("getAllArticles function ... ");
        ReturnTemplate returnTemplate = new ReturnTemplate();

        List<Article> articles = articleService.findAllArticles();

        articles
            .parallelStream()
            .forEach(param -> setArticleTagClassifyComment(param));

        returnTemplate.addData("articles",articles);

        log.info("getAllArticles function ... returnData  = " + returnTemplate.toString() );

        return  returnTemplate;
    }

    /**
     * 给文章设置 Tag, Classify,Comment,因为这些都是手动维护，不是数据库帮维护
     * @param article
     */
    public void setArticleTagClassifyComment(Article article) {

        //得到该文章的 classify
        Classify classify = classifyService.findClassifyById(article.getClassifyId());
        article.setClassifyName(classify.getName());

        //得到该文章的 tag
        List<Article_Tag> article_tags = article_tagService.findByArticleId(article.getId());
        final List<Tag> tags = new LinkedList<Tag>();

        article_tags
                .parallelStream()
                .forEach(param ->{
                    Tag tag = tagService.findTagById(param.getTagId());
                    tags.add(tag);
                });

        article.setTags(tags);

        //得到该文章的 comment
        List<Comment> comments = commentService.findByArticleId(article.getId());
        article.setComments(comments);
        article.setCommentNum(comments.size());
    }

    @RequestMapping(value = "/article/get-recommend-articles",method = RequestMethod.GET)
    public Object findRecommendArticles(){
        ReturnTemplate returnTemplate = new ReturnTemplate();
        returnTemplate.addData("recommendArticles",articleService.findRecommendArticles());
        return  returnTemplate;
    }

}
