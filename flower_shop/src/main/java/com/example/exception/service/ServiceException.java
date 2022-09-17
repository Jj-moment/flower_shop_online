package com.example.exception.service;

import com.example.exception.ErrorCode;
import com.example.exception.code.ResultCode;
import lombok.Getter;

/**
 * @author lulu
 */
@Getter
public class ServiceException extends RuntimeException {
    private final int code;
    private final String msg;

    /**
     * 自定义异常
     */
    public ServiceException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    /**
     * 使用默认的异常状态码
     */
    public ServiceException(String message) {
        super(message);
        this.code = ResultCode.FAILED.getCode();
        this.msg = ResultCode.FAILED.getMsg();
    }
}
