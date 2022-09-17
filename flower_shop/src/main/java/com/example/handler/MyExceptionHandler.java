package com.example.handler;

import com.example.exception.service.ServiceException;
import com.example.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 抛出自定义异常
 * </p>
 *
 * @author cc
 * @since 2022-02-08 08:34:13
 */
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler({ServiceException.class})
    public Result<String> serviceException(ServiceException e) {
        return Result.success(e.getCode(), e.getMsg(), e.getMessage());
    }
}