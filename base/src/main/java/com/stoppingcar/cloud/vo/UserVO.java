package com.stoppingcar.cloud.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@Data
@ApiModel("/用户信息")
public class UserVO {
    @ApiModelProperty("用户Id")
    private Long id;
    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("用户角色")
    private String role;
    @ApiModelProperty("token")
    private String token;
}
