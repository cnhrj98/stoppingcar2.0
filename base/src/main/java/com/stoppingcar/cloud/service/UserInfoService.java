package com.stoppingcar.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stoppingcar.cloud.damain.UserInfo;
import com.stoppingcar.cloud.dto.UpdatePasswordDTO;
import com.stoppingcar.cloud.vo.UserVO;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 获取用户信息
     *
     * @param username 用户名称
     * @return 用户信息
     */
    UserVO getUserInfo(String username);

    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息
     */
    UserVO getNowUser();

    /**
     * 修改用户密码
     *
     * @param dto 密码信息
     * @return 修改结果
     */
    Boolean updatePassword(UpdatePasswordDTO dto);
}