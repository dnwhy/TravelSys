package com.etc.travelsys.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = BindException.class)
    public Object bindException(BindException ex){
        BindingResult bindingResult = ex.getBindingResult();
        //获取所有的错误信息ex.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<String> lists = new ArrayList<>();
        //遍历并把错误信息添加到集合中
        for (ObjectError e : allErrors){
            lists.add(e.getDefaultMessage());
        }
        return lists;
    }
}
