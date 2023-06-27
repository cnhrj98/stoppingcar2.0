package com.stoppingcar.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author JerryHeng
 * @since 2023/6/27
 */
@Data
public class UpdatePasswordDTO {
    @ApiModelProperty("手机验证码")
    private String smsCode;
    @ApiModelProperty("旧密码")
    private String oldPassword;
    @ApiModelProperty("新密码")
    private String newPassword;
}
