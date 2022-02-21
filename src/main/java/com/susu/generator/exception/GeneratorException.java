package com.susu.generator.exception;

public class GeneratorException extends RuntimeException{

    private Integer code = 500;

    private String msg;

    public GeneratorException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public GeneratorException(String msg,Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public GeneratorException(Integer code,String msg) {
        super(msg);
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
