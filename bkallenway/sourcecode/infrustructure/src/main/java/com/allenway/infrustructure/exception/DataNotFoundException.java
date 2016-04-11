package com.allenway.infrustructure.exception;

/**
 * Created by wuhuachuan on 16/3/29.
 */
public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(){}
    public DataNotFoundException(String message){
        super(message);
    }
}
