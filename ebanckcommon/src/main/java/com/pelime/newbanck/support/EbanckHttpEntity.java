package com.pelime.newbanck.support;

public class EbanckHttpEntity {
    private String code;
    private Object body;

    public EbanckHttpEntity(String code, Object body) {
        this.code = code;
        this.body = body;
    }

    public EbanckHttpEntity() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
