package controller;

import com.allenway.controller.LoginController;
import com.allenway.entity.Admin;
import com.allenway.service.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

/**
 * Created by wuhuachuan on 16/3/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class LoginControllerTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private LoginController loginController;

    @Before
    public void init(){
        loginController.setAdminService(adminService);
    }

    @Test
    public void login() throws Exception {

        Admin admin = new Admin();

        //测试帐号密码为空的情况
        admin.setUsername("");
        admin.setPassword("");
        assertTrue("return string doesn't contain statusCode 2000!",loginController.login(admin).toString().contains("2000"));

        //测试sql攻击的情况
        admin.setUsername("");
        admin.setPassword("\"");
        assertTrue("return string doesn't contain statusCode 2000!",loginController.login(admin).toString().contains("2000"));

        //测试错误的帐号密码
        admin.setUsername("admin");
        admin.setPassword("123456");
        assertTrue("return string doesn't contain statusCode 2002!",loginController.login(admin).toString().contains("2002"));

        //测试正确的情况
        admin.setUsername("admin");
        admin.setPassword("admin");
        assertTrue("can not login with correct username and password!",!loginController.login(admin).toString().contains("admin"));
    }
}