package com.allenway.utils.response;

/**
 * Created by wuhuachuan on 16/3/3.
 */
public enum ReturnStatusCode {

    SUCCESS(0),  //代表成功的请求

    /**
     * 服务器端报错
     */
    RuntimeException(1000),
    DataNotFoundException(1001),
    IllegalArgumentException(1002),

    /**
     * 业务逻辑出错
     */
    USERNAME_PASSWORD_WRONG(2002), //登录的用户名密码错误
    CLASSIFY_HAS_ARTICLE_OR_SUBCLASSIFY(2003); //分类无法删除,因为有子分类或者该分类下有文章

    private int code;

    private ReturnStatusCode(int code){this.code = code;}

    public int getCode(){
        return this.code;
    }
}
