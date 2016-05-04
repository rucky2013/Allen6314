package com.allenway.infrustructure.controller;

import com.allenway.infrustructure.entity.Record;
import com.allenway.infrustructure.service.RecordService;
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
@RequestMapping(value = "auth/operation")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 得到所有的操作记录
     * @return
     */
    @RequestMapping(value = "/get-records",method = RequestMethod.GET)
    public Object findRecords(){

        ReturnTemplate returnTemplate = new ReturnTemplate();
        List<Record> records = recordService.findAllRecords();

        returnTemplate.addData("records",records);

        log.info("findRecords function ... returnData = {}.",returnTemplate.toString());

        return returnTemplate;
    }
}
