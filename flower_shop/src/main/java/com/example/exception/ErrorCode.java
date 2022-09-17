package com.example.exception;

/**
 * @author cc
 */
public interface ErrorCode {

    /**
     * 获取状态码
     *
     * @return 状态码信息
     */
    int getCode();

    /**
     * 获取异常消息
     *
     * @return 消息
     */
    String getMsg();
}