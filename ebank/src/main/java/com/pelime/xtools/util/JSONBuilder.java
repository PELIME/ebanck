package com.pelime.xtools.util;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class JSONBuilder {
    private Map<String,Object> object;
    public JSONBuilder(){
        object=new HashMap<>();
    }
    public static JSONBuilder  create(){
        return new JSONBuilder();
    }

    public JSONBuilder element(String name,Object value){
        object.put(name,value);
        return  this;
    }

    public Object toJSON(){
        return JSON.toJSON(object);
    }

    public Map<String,Object> toMap(){
        return object;
    }
}
