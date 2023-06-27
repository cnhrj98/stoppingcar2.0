package com.stoppingcar.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author JerryHeng
 * @since 2023/6/27
 */
@Data
public class RegisteredDTO {
    @ApiModelProperty("手机号")
    private String phone;
//    @ApiModelProperty("密码")
//    private String password;
    @ApiModelProperty("用户类型")
    private String role;
//    @ApiModelProperty("用户名")
//    private String username;
}
