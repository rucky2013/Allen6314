package com.allenway.controller;

import com.allenway.entity.Admin;
import com.allenway.service.AdminService;
import com.allenway.utils.ReturnTemplate;
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

    /**
     * 测试查找管理员函数
     * @throws Exception
     */
    @Test
    public void findAdmin() throws Exception {

        Admin admin = adminService.findAdmin();

        assertNotNull("admin = null!",admin);

        ReturnTemplate returnTemplate = new ReturnTemplate();

        returnTemplate.addData("admin",admin);

        System.out.println("Admin = " + returnTemplate.toString());
    }

    /**
     * 测试更新管理员函数（不包括密码字段）
     * @throws Exception
     */
    @Test
    public void updateAdmin() throws Exception {

        Admin admin = adminService.findAdmin();

        assertNotNull("admin = null!",admin);

        admin.setEmail("wuhuachuan713@163.com");

        adminService.updateAdmin(admin);

        assertEquals("update fail!",admin.getEmail(),"wuhuachuan713@163.com");

    }
}