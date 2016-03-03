package com.allenway.controller;

import com.allenway.entity.Admin;
import com.allenway.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by wuhuachuan on 16/3/3.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class AdminControllerTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void findAdmin() throws Exception {

        Admin admin = adminService.findAdmin();

        assertNotNull("admin = null!",admin);

        System.out.println("Admin = " + admin);
    }
}