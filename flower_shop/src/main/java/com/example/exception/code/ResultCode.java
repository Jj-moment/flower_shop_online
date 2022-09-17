package com.example.exception.code;

import com.example.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author comment
 * @date 2022/8/13 17:27
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements ErrorCode {
    /*请求成功*/
    SUCCESS(0, "请求成功"),
    /*请求失败*/
    FAILED(401, "请求失败"),
    /*参数校验失败*/
    VALIDATE_ERROR(1002, "参数校验失败"),
    /*系统异常*/
    SYSTEM_ERROR(1003, "系统异常"),
    /*包装返回失败*/
    RESPONSE_PACK_ERROR(1004, "response返回包装失败");

    private final int code;
    private final String msg;

}
