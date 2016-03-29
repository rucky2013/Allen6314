package com.allenway.controller;

import com.allenway.entity.Admin;
import com.allenway.infrustructure.DataNotFoundException;
import com.allenway.service.AdminService;
import com.allenway.utils.ReturnTemplate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by wuhuachuan on 16/3/3.
 */

@Slf4j
@Data
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 查找管理员（返回值不包括密码字段）
     * @return
     */
    @RequestMapping(value = "/find-admin",method = RequestMethod.GET)
    public Object findAdmin(){

        Admin admin = adminService.findAdmin();

        ReturnTemplate returnTemplate = new ReturnTemplate();

        if(admin == null) {
            throw new DataNotFoundException("admin == null");
        } else {
            //decorate 函数:去除密码字段（即不返回密码字段给前端）
            returnTemplate.addData("admin", decorate(admin));
        }

        log.info("findAdmin() ... return admin json = {}",returnTemplate.toString());

        return returnTemplate;
    }

    /**
     * 去除密码字段（即不返回密码字段给前端）
     * @param admin
     * @return
     */
    private HashMap<String,Object> decorate(Admin admin) {

        HashMap<String,Object> hashMap = new HashMap<String, Object>();

        hashMap.put("id",admin.getId());
        hashMap.put("username",admin.getUsername());
        hashMap.put("email",admin.getEmail());
        hashMap.put("qq",admin.getQq());
        hashMap.put("wechat",admin.getWechat());
        hashMap.put("github",admin.getGithub());
        hashMap.put("nickName",admin.getNickName());
        hashMap.put("birthday",admin.getBirthday());

        return hashMap;
    }

    /**
     * 更新管理员信息（不包括密码,密码有另外专门的函数）
     * @param admin
     * @return
     */
    @RequestMapping(value = "/update-admin",method = RequestMethod.POST)
    public Object updateAdmin(@RequestParam Admin admin){

        ReturnTemplate returnTemplate = new ReturnTemplate();

        if(validParam(admin)){
            adminService.updateAdmin(admin);
        } else {
            throw new IllegalArgumentException("Param is invalid!");
        }

        return returnTemplate;
    }

    /**
     * 验证更新管理员的字段是否符合常理
     * @param admin
     * @return
     */
    private boolean validParam(Admin admin) {
        if(StringUtils.isEmpty(admin.getId()) || StringUtils.isEmpty(admin.getUsername())){
            return false;
        } else {
            return true;
        }
    }
}
