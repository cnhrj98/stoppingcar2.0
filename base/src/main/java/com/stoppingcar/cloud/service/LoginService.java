package com.stoppingcar.cloud.service;

import com.stoppingcar.cloud.dto.LoginDTO;
import com.stoppingcar.cloud.dto.RegisteredDTO;
import com.stoppingcar.cloud.vo.UserVO;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
public interface LoginService {
    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return 用户信息
     */
    UserVO login(LoginDTO loginDTO);

    /**
     * 注销登录
     *
     * @return 注销结果
     */
    Boolean logout();

    /**
     * 用户注册
     *
     * @param dto 注册信息
     * @return 用户信息
     */
    UserVO registered(RegisteredDTO dto);
}
