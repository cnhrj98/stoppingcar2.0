package com.stoppingcar.cloud.util;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.stoppingcar.cloud.damain.base.AuthUser;
import com.stoppingcar.cloud.damain.enums.LoginConstant;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
public class UserContext {
    public static Long getUserId(){
        return StpUtil.getLoginIdAsLong();
    }

    public static AuthUser getUser(){
        SaSession session = StpUtil.getSession();
        String loginName = session.get(LoginConstant.LOGIN_NAME).toString();
        String userId = session.get(LoginConstant.USER_ID).toString();
        String role = session.get(LoginConstant.ROLE).toString();
        AuthUser user = new AuthUser();
        user.setRole(role);
        user.setNickname(loginName);
        user.setUserId(userId);
        return user;
    }
}
