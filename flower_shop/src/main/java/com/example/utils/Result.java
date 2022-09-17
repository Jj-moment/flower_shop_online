package com.example.utils;

import com.example.exception.code.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 蒋蒋
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    int code;
    String msg;
    long total;
    T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), 1, data);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(ResultCode.SUCCESS.getCode(), msg, 1, data);
    }

    public static <T> Result<T> success(T data, long count) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), count, data);
    }

    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<>(code, msg, 1, data);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMsg(), 1, data);
    }
}
