package com.allenway.utils;

import com.google.gson.Gson;
import lombok.Data;

import java.util.HashMap;

/**
 * Created by wuhuachuan on 16/3/3.
 */
@Data
public class ReturnTemplate {

    private ReturnStatusCode statusCode;  //必须
    private String errorMsg;   // 可选（前端可以从ReturnErrorCode参考表中得出）
    private HashMap<String,Object> returnData; // 可选

    private static Gson gson = new Gson();

    /**
     * 构造函数 : 默认创建是一个成功的请求
     */
    public ReturnTemplate(){
        this.statusCode = ReturnStatusCode.SUCCESS;
        //returnData 的 HashMap 到真正需要返回数据的时候创建
    }

    /**
     *
     * 构造函数 : 用于创建一个错误的返回,不带错误信息
     * @param statusCode
     */
    public ReturnTemplate(ReturnStatusCode statusCode){
        this.statusCode = statusCode;
        //returnData 的 HashMap 到真正需要返回数据的时候创建
    }

    /**
     * 构造函数 : 用于创建一个错误的返回,带错误信息
     * @param statusCode
     * @param errorMsg
     */
    public ReturnTemplate(ReturnStatusCode statusCode, String errorMsg){
        this.statusCode = statusCode;
        this.errorMsg = errorMsg;
        //returnData 的 HashMap 到真正需要返回数据的时候创建
    }

    /**
     * 构造函数 : 使用Builder模式创建的ReturnTemplate
     * @param builder
     */
    public ReturnTemplate(Builder builder){
        this.statusCode = builder.statusCode;
        this.errorMsg = builder.errorMsg;
        this.returnData = builder.returnData;
    }

    /**
     * 用于添加返回数据
     * @param key
     * @param obj
     */
    public void addData(String key,Object obj){
        if(returnData == null){
            returnData = new HashMap<String,Object>();
        }
        returnData.put(key,obj);
    }

    //Builder 模式创建 ReturnTemplate
    public static class Builder{

        private ReturnStatusCode statusCode;
        private String errorMsg;  //
        private HashMap<String,Object> returnData = new HashMap<String,Object>();

        private Builder statusCode(ReturnStatusCode statusCode){
            this.statusCode = statusCode;
            return this;
        }
        private Builder errorMsg(String errorMsg){
            this.errorMsg = errorMsg;
            return this;
        }
        private Builder returnData(HashMap<String,Object> returnData) {
            this.returnData = returnData;
            return this;
        }
        public ReturnTemplate build(){
            return new ReturnTemplate(this);
        }
    }

    public String toString(){

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("statusCode", statusCode.getCode());
        map.put("errorMsg", errorMsg);
        map.put("data", returnData);

        return gson.toJson(map);
    }

}

