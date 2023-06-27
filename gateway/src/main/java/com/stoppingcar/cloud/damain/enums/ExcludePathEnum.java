package com.stoppingcar.cloud.damain.enums;

import lombok.Getter;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@Getter
public enum ExcludePathEnum {
    //不进行鉴权的接口
    TEMP_TOKEN("/tempToken", "获取临时token的接口"),
    VERIFY_IMG("/getVerifyImg", "获取验证码图片的接口"),
    LOGIN("/auth/login", "登录接口"),
    LOGOUT("/auth/logout", "注销登录接口"),
    REGISTERED("/auth/registered","注册接口");

    private final String path;
    private final String remark;

    ExcludePathEnum(String path, String remark) {
        this.path = path;
        this.remark = remark;
    }
}
