package com.yyt.example.common;

import java.io.Serializable;

public class Res implements Serializable{

    private static final long serialVersionUID = 3799575195766658856L;

    private Integer code;

    private String msg;

    private Object data;

    public Res() {
    }

    public Res(ResEnum resEnum) {
        this.code = resEnum.getCode();
        this.msg = resEnum.getMsg();
    }

    public Res(ResEnum resEnum,Object data) {
        this.code = resEnum.getCode();
        this.msg = resEnum.getMsg();
        this.data = data;
    }

    public static Res ok(){
        return new Res(ResEnum.OK);
    }

    public static Res ok(Object data){
        return new Res(ResEnum.OK,data);
    }

    public static Res error(){
        return new Res(ResEnum.ERROR);
    }

    public static Res error(String msg){
        Res res = new Res();
        res.code = ResEnum.ERROR.getCode();
        res.msg = msg;
        return res;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(ResEnum resEnum) {
        this.code = resEnum.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(ResEnum resEnum) {
        this.msg = resEnum.getMsg();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
