package com.allenway.controller;

import com.allenway.entity.Admin;
import com.allenway.service.AdminService;
import com.allenway.utils.ReturnTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wuhuachuan on 16/3/3.
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/find-admin",method = RequestMethod.GET)
    public String findAdmin(){
        Admin admin = adminService.findAdmin();

        ReturnTemplate returnTemplate = new ReturnTemplate();

        returnTemplate.addData("admin",admin);

        return returnTemplate.toString();
    }
}
