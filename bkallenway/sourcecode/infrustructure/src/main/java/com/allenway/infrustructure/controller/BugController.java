package com.allenway.infrustructure.controller;

import com.allenway.infrustructure.entity.Bug;
import com.allenway.infrustructure.service.BugService;
import com.allenway.utils.response.ReturnTemplate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/11.
 */
@Data
@Slf4j
@RestController
@RequestMapping(value = "/bug")
public class BugController {

    @Autowired
    private BugService bugService;

    @RequestMapping(value = "/get-all-bugs",method = RequestMethod.GET)
    public Object findAllBugs(){

        ReturnTemplate returnTemplate = new ReturnTemplate();
        List<Bug> bugList = bugService.findAllBugs();

        log.info("findAllBugs function... returnData = {}.",bugList.toString());

        returnTemplate.addData("bugs",bugList);
        return returnTemplate;

    }

}
