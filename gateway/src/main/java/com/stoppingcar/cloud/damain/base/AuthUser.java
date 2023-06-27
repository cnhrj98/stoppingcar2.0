
package com.stoppingcar.cloud.damain.base;

import lombok.Data;

/**
 * @author rh 扩展用户信息
 */
@Data
public class AuthUser {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickname;

    private String role;

    private String userType;
}
