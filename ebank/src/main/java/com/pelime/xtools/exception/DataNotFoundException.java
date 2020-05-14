package com.pelime.xtools.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(){
        super("未找到数据");
    }
}
