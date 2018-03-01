package com.yyt.example.common;

public enum ResEnum {

    OK(0,"请求成功"),
    ERROR(500,"请求失败"),
    ;

    private Integer code;

    private String msg;

    ResEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
