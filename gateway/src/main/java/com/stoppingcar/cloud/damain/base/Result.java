package com.stoppingcar.cloud.damain.base;

import lombok.Data;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {
        this.code = 200;
        this.message = "success";
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> success(Integer code, String message) {
        return new Result<>(code, message);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(500, message);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message);
    }
}
