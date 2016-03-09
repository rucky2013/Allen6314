package com.allenway.controller;

import com.allenway.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

/**
 * Created by wuhuachuan on 16/3/9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class ArticleControllerTest {

    @Autowired
    private ArticleController articleController;

    @Test
    public void addArticle() throws Exception {

        Article article = new Article();

        //测试正常保存
        article.setTitle("testArticle");
        article.setClassifyId("1234567890");
        article.setContent("testContent");
        assertTrue("add article fail!!!",articleController.saveArticle(article).contains("\"statusCode\":0"));

        //测试缺少参数情况下的保存
        article.setTitle("");
        article.setClassifyId("");
        article.setContent("");
        assertTrue("return string doesn't contain statusCode 2000!",articleController.saveArticle(article).contains("2000"));
    }

    @Test
    public void deleteArticle() throws Exception{

        String id = "";
        assertTrue("id = \"\" ,but return string doesn't contain 2000", articleController.deleteArticleById(id).contains("2000"));

        id = "\'";
        assertTrue("id contains \" ,but return string doesn't contain 2000", articleController.deleteArticleById(id).contains("2000"));

        id = "ssss";
        assertTrue("id is wrong ,but return string doesn't contain 2001", articleController.deleteArticleById(id).contains("2001"));

        //测试id正确的情况,需要手动看数据库这个 id 是多少
        id = "07610ab4-8082-4d45-8ac4-09d514772649";
        assertTrue("delete success!!", articleController.deleteArticleById(id).contains("\"statusCode\":0"));
    }

    @Test
    public void findArticleById() throws Exception{
        String id = "";
        assertTrue("id = \"\" ,but return string doesn't contain 2000", articleController.findArticleById(id).contains("2000"));

        id = "\'";
        assertTrue("id contains \" ,but return string doesn't contain 2000", articleController.findArticleById(id).contains("2000"));

        id = "ssss";
        assertTrue("id is wrong ,but return string doesn't contain 2001", articleController.findArticleById(id).contains("2001"));

        //测试id正确的情况,需要手动看数据库这个 id 是多少
        id = "8ab4783c-958c-4c65-8894-cfd6a8de9e56";
        assertTrue("id is correct, but couldn't find the article !!", articleController.findArticleById(id).contains("article"));
    }
}