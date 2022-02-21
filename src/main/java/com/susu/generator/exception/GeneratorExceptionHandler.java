package com.susu.generator.exception;

import com.susu.generator.common.Result;
import com.susu.generator.common.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GeneratorExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(GeneratorException.class)
    @ResponseBody
    public Result error(GeneratorException e){
        e.printStackTrace();
        if(e.getCode() == null){
            return Result.error(e.getMsg());
        }else {
            if (StringUtils.isNotEmpty(e.getMsg())){
                return Result.error(e.getCode(),e.getMsg());
            }
            return Result.error(e.getCode(),"系统异常");
        }

    }

}
