package com.allenway.visitor.controller;

import com.allenway.utils.response.ReturnTemplate;
import com.allenway.visitor.service.TagService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuhuachuan on 16/4/2.
 */

@Slf4j
@Data
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 查找全部的 tag
     * @return
     */
    @RequestMapping(value = "/tag/find-all-tags",method = RequestMethod.GET)
    public Object findAllTags(){

        ReturnTemplate returnTemplate = new ReturnTemplate();
        returnTemplate.addData("tags",tagService.findAllTags());
        return  returnTemplate;
    }
}
