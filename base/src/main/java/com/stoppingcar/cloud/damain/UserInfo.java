package com.stoppingcar.cloud.damain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色 admin -->后台用户  user -->App用户
     */
    private String role;

    /**
     * 删除标识
     */
    private Boolean deleted;
}
