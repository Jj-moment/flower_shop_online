package com.example.exception.code;

import com.example.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author comment
 * @date 2022/8/13 17:30
 */
@Getter
@AllArgsConstructor
public enum ServiceCode implements ErrorCode {
    /*用户名冲突*/
    USERNAME_CONFLICT(402,"用户名冲突"),
    /*电话号码冲突*/
    USER_PHONE_CONFLICT(403,"电话号码冲突"),
    /*用户名未找到*/
    LOGIN_EXCEPTION(405,"登录异常"),
    /*用户名未找到*/
    REGISTER_EXCEPTION(405,"注册异常"),
    /*插入信息异常*/
    INSERT_EXCEPTION(406,"插入异常"),
    /*未找到资源*/
    NO_FOUNT_RESOURCE(404,"未找到资源");

    private final int code;
    private final String msg;
}
