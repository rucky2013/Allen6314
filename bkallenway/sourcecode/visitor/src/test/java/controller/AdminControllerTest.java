package controller;

import com.allenway.controller.AdminController;
import com.allenway.entity.Admin;
import com.allenway.service.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by wuhuachuan on 16/3/3.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class AdminControllerTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminController adminController;

    @Before
    public void init(){
        adminController.setAdminService(adminService);
    }

    /**
     * 测试查找管理员函数
     * @throws Exception
     */
    @Test
    public void findAdmin() throws Exception {
        assertTrue("admin is't found! It's impossible !! ",adminController.findAdmin().toString().contains("admin"));
    }

    /**
     * 测试更新管理员函数（不包括密码字段）
     * @throws Exception
     */
    @Test
    public void updateAdmin() throws Exception {

        Admin admin = adminService.findAdmin();

        assertNotNull("admin is't found! It's impossible !! ",admin);

        admin.setEmail("wuhuachuan714@163.com");

        assertTrue("admin update fail !! ",adminController.updateAdmin(admin).toString().contains("\"statusCode\":0"));


    }
}