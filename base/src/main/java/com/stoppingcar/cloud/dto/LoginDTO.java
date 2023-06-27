package com.stoppingcar.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@Data
public class LoginDTO {
    @ApiModelProperty("电话号码")
    private String phone;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("短信验证码")
    private String smsCode;
}
