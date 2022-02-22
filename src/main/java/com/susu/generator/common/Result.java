package com.susu.generator.common;


import lombok.Data;

/**
 * <p>Description: Result</p>
 * <p>请求响应体</p>
 * @author sujay
 * @email fxbsujay@gmail.com
 * @date 9:40 2022/2/22
 * @version 1.0
 */
@Data
public class Result  {

    /**
     * 成功编码
     **/
    public static final int SUCCESS_CODE = 200;

    /**
     * 失败编码
     **/
    public static final int ERROR_CODE = 500;

    /**
     * 编码
     **/
    private int code = SUCCESS_CODE;

    /**
     * 消息内容
     **/
    private String msg = "操作成功";

    /**
     * 数据
     **/
    private Object data;

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object data) {
        Result result = new Result();
        result.setData(data);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg("操作失败");
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }

    public static Result error(Integer code,String msg) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
