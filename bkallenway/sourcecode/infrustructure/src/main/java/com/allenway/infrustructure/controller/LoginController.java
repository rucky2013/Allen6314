package com.allenway.infrustructure.controller;

import com.allenway.infrustructure.service.AdminService;
import com.allenway.utils.encryption.DESEncryptUtil;
import com.allenway.utils.response.ReturnStatusCode;
import com.allenway.utils.response.ReturnTemplate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allenway.infrustructure.entity.Admin;

/**
 * Created by wuhuachuan on 16/3/8.
 */

@Data
@Slf4j
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录
     * @param admin
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Object login(Admin admin){

        boolean isParamValid = validLoginAdminParam(admin);

        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(isParamValid){

            Admin ad =  validUsernamePassword(admin.getUsername(),admin.getPassword());
            if(ad == null){
                returnTemplate.setStatusCode(ReturnStatusCode.USERNAME_PASSWORD_WRONG);
                return returnTemplate;
            } else {
                returnTemplate.addData("admin",ad);
                return returnTemplate;
            }

        } else {
            throw new IllegalArgumentException("Param is invalid!");
        }
    }

    /**
     * 验证登录用户的用户名,密码
     * @param username
     * @param password
     * @return
     */
    private Admin validUsernamePassword(String username, String password) {

        Admin dbAdmin = adminService.findAdminByUsername(username);

        if(dbAdmin == null || !DESEncryptUtil.matchPassphrase(dbAdmin.getPassword(),dbAdmin.getSalt(),password)){
            return null;
        } else {
            return dbAdmin;
        }
    }

    /**
     * 验证登录用户的 用户名,密码信息是否合理
     * @param admin
     * @return
     */
    private boolean validLoginAdminParam(Admin admin) {
        if(StringUtils.isEmpty(admin.getUsername()) || StringUtils.isEmpty(admin.getPassword())){
            return false;
        //防止sql 注入攻击
        } else if(admin.getPassword().contains("\"") || admin.getPassword().contains("\'")){
            return false;
        } else {
            return true;
        }
    }
}
