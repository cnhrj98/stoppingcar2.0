package com.stoppingcar.cloud.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stoppingcar.cloud.damain.UserInfo;
import com.stoppingcar.cloud.dto.UpdatePasswordDTO;
import com.stoppingcar.cloud.exception.StopException;
import com.stoppingcar.cloud.mapper.UserInfoMapper;
import com.stoppingcar.cloud.service.UserInfoService;
import com.stoppingcar.cloud.util.RedisUtil;
import com.stoppingcar.cloud.util.UserContext;
import com.stoppingcar.cloud.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Resource
    private RedisUtil redisUtil;

    @Override
    public UserVO getUserInfo(String username) {
        UserVO userVO = new UserVO();
        UserInfo userInfo = getOne(Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getUsername, username)
                .eq(UserInfo::getDeleted, false));
        if (ObjectUtil.isNotNull(userInfo)) {
            BeanUtils.copyProperties(userInfo, userVO);
        }
        return userVO;
    }

    @Override
    public UserVO getNowUser() {
        //获取当前登录用户
        Long userId = UserContext.getUserId();
        UserInfo userInfo = getById(userId);
        UserVO userVO = new UserVO();
        if (ObjectUtil.isNotNull(userInfo)) {
            BeanUtils.copyProperties(userInfo, userVO);
        }
        return userVO;
    }

    @Override
    public Boolean updatePassword(UpdatePasswordDTO dto) {
        //获取当前登录用户
        Long userId = UserContext.getUserId();
        UserInfo userInfo = getById(userId);
        if (StrUtil.isEmpty(dto.getOldPassword()) && StrUtil.isEmpty(dto.getSmsCode())) {
            throw new StopException("请输入原始密码或手机验证码");
        }
        //验证码校验
        if (StrUtil.isNotEmpty(dto.getSmsCode())) {
            String smsCode = redisUtil.get("SMS_CODE" + userInfo.getPhone());
            if (StrUtil.isEmpty(smsCode) || !smsCode.equals(dto.getSmsCode())) {
                throw new StopException("验证码错误");
            }
        }
        //旧密码校验
        if (StrUtil.isNotEmpty(dto.getOldPassword())) {
            if (!dto.getOldPassword().equals(userInfo.getPassword())) {
                throw new StopException("原始密码错误");
            }
        }
        userInfo.setPassword(dto.getNewPassword());
        StpUtil.logout(userInfo.getId());
        return updateById(userInfo);
    }
}
