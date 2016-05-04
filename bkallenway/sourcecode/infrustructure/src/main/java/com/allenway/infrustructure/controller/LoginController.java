package com.allenway.infrustructure.controller;

import com.allenway.infrustructure.entity.Admin;
import com.allenway.infrustructure.entity.OAuthParamEntity;
import com.allenway.infrustructure.entity.TokenEntity;
import com.allenway.infrustructure.service.AdminService;
import com.allenway.infrustructure.ssl.SSLCertificateValidation;
import com.allenway.utils.encryption.DESEncryptUtil;
import com.allenway.utils.response.ReturnStatusCode;
import com.allenway.utils.response.ReturnTemplate;
import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @Autowired(required = false)
    private OAuthParamEntity oauthParamEntity;

    private Gson gson = new Gson();

    /**
     * 登录
     * @param admin
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Object login(Admin admin) throws IOException {

        boolean isParamValid = validLoginAdminParam(admin);

        ReturnTemplate returnTemplate = new ReturnTemplate();
        if(isParamValid){

            Admin ad =  validUsernamePassword(admin.getUsername(),admin.getPassword());
            if(ad == null){
                returnTemplate.setStatusCode(ReturnStatusCode.USERNAME_PASSWORD_WRONG);
                return returnTemplate;
            } else {
                // get oauth token
                TokenEntity tokenEntity = getToken(ad.getId());

                returnTemplate.addData("token",tokenEntity);
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

    /**
     * 拿到 token
     * @return
     */
    public TokenEntity getToken(String adminId) throws IOException {

        //去除证书验证（注意：生产环境下需要进行证书验证）
        SSLCertificateValidation.disable();

        HttpURLConnection connection;
        connection = (HttpURLConnection) new URL(oauthParamEntity.getOauthTokenApiURL()).openConnection();
        sendConnectionParams(connection,adminId);
        connection.connect();

        //获取返回值
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lines;
        String result = "";
        while ((lines = reader.readLine()) != null) {
            if(lines != null){
                result = result + lines;
            }
        }

        reader.close();
        connection.disconnect();

        return gson.fromJson(result, TokenEntity.class);
    }

    /**
     * 向OAuth 发送参数
     * @param connection
     * @throws IOException
     */
    private void sendConnectionParams(HttpURLConnection connection,String adminId) throws IOException {

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        String param =
                "client_id="+ oauthParamEntity.getClientId() +"&"+
                        "client_secret="+ oauthParamEntity.getClientSecret() +"&"+
                        "grant_type="+ oauthParamEntity.getGrantType() +"&"+
                        "provision_key="+ oauthParamEntity.getProvisionKey() +"&"+
                        "authenticated_userid="+adminId +"&"+
                        "scope=" + oauthParamEntity.getScope();

        OutputStream targetOS = connection.getOutputStream();
        try {
            targetOS.write(param.getBytes());
            targetOS.flush();
        } finally {
            targetOS.close();
        }

    }
}
