package com.miyox.security.bean;

public class ResponseMsg {

    private boolean state;
    private String msg;
    private Integer code;

    public ResponseMsg() {
    }

    public ResponseMsg(boolean state, String msg) {
        this.state = state;
        this.msg = msg;
        this.code = state ? 0 : -1;
    }

    public ResponseMsg(boolean state, String msg, Integer code) {
        this.state = state;
        this.msg = msg;
        this.code = code;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
