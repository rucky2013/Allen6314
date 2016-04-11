package controller;

import com.allenway.infrustructure.controller.BugController;
import com.allenway.infrustructure.service.BugService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by wuhuachuan on 16/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class BugControllerTest {

    @Autowired
    private BugController bugController;

    @Autowired
    private BugService bugService;

    @Before
    public void init(){
        bugController.setBugService(bugService);
    }

    @Test
    public void findAllBugs() throws Exception {
        assertTrue("findAllBugs fail",bugController.findAllBugs().toString().contains("bugs"));
    }
}