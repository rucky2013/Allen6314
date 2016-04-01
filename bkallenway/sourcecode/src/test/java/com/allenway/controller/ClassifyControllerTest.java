package com.allenway.controller;

import com.allenway.entity.Classify;
import com.allenway.service.ClassifyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by wuhuachuan on 16/3/9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class ClassifyControllerTest {

    @Autowired
    private ClassifyController classifyController;

    @Autowired
    private ClassifyService classifyService;

    @Before
    public void init(){
        classifyController.setClassifyService(classifyService);
    }

    @Test
    public void addClassify() throws Exception {

        Classify classify = new Classify();

        //测试为空的情况
        assertTrue("id = \"\" ,but return string doesn't contain 2000",classifyController.saveClassify(classify).toString().contains("2000"));


        //测试正常保存的情况
        classify.setName("testClassifyName2");
        classify.setParentClassifyId("11111");
        assertTrue("id = \"\" ,but return string doesn't contain 2000",classifyController.saveClassify(classify).toString().contains("\"statusCode\":0"));
    }

    @Test
    public void deleteClassifyById() throws Exception{

        String id = "";

        //测试id为空的情况
        assertTrue("id = \"\" ,but return string doesn't contain 2000",classifyController.deleteClassifyById(id).toString().contains("2000"));

        //测试有子类 id 的情况下
        id = "c73f6168-7fa5-454c-ab84-fad42393f26e";
        assertTrue("classify has sub classify,but delete success!!",classifyController.deleteClassifyById(id).toString().contains("2003"));

        //测试有 文章的 情况下
        id = "14dfdc38-303b-4a45-ba97-667c819b8c16";
        assertTrue("classify has article ,but delete success!!",classifyController.deleteClassifyById(id).toString().contains("2003"));

        //测试正常删除的情况
        id = "e9e64518-fa8f-4ceb-8006-50af92bf7e35";
        assertTrue("classify has on articles or sub classifies ,but delete fail!!",classifyController.deleteClassifyById(id).toString().contains("\"statusCode\":0"));
    }

    @Test
    public void findClassifyById() throws  Exception{
        String id = "";

        //测试id为空的情况
        assertTrue("id = \"\" ,but return string doesn't contain 2000",classifyController.findClassifyById(id).toString().contains("2000"));


        //测试id是错误的情况下
        id = "123";
        assertTrue("id is wrong ,but return string doesn't contain 2001",classifyController.findClassifyById(id).toString().contains("2001"));

        //测试id是正常的情况下
        id = "14dfdc38-303b-4a45-ba97-667c819b8c16";
        assertTrue("id is correct ,but return string doesn't contain the data",classifyController.findClassifyById(id).toString().contains("classify"));
    }


    @Test
    public void findAllFirstLevelClassifies(){
        assertTrue("Some exception occurs in the findAllFirstLevelClassifies method!",classifyController.findAllFirstLevelClassifies().toString().contains("classifies"));
    }


}