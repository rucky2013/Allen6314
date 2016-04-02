package com.allenway.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by wuhuachuan on 16/4/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class TagControllerTest {

    @Autowired
    private TagController tagController;

    @Test
    public void findAllTags() throws Exception {
        assertTrue("Some exception occurs in the findAllFirstLevelClassifies method!",tagController.findAllTags().toString().contains("tags"));
    }
}