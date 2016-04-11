package controller;

import com.allenway.infrustructure.controller.RecordController;
import com.allenway.infrustructure.service.RecordService;
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

public class RecordControllerTest {

    @Autowired
    private RecordController recordController;

    @Autowired
    private RecordService recordService;

    @Before
    public void init(){
        recordController.setRecordService(recordService);
    }

    @Test
    public void findRecords() throws Exception {
        assertTrue("find all  records fail",recordController.findRecords().toString().contains("records"));
    }
}