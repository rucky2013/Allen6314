package com.allenway.utils;

/**
 * Created by wuhuachuan on 16/3/3.
 */
public enum ReturnStatusCode {

    SUCCESS(0),  //代表成功的请求

    /**
     * 服务器端报错
     */
//    XXX(1000), //后端空指针错误

    /**
     * 业务逻辑出错
     */
    PARAM_INVALID(2000), //前端传来的参数有误，例如为""，null 或者带有 "" 或者 ''(sql攻击的标志)
    DATA_IS_NOT_FOUND(2001),  //数据找不到
    USERNAME_PASSWORD_WRONG(2002); //登录的用户名密码错误

    private int code;

    private ReturnStatusCode(int code){this.code = code;}

    public int getCode(){
        return this.code;
    }
}
