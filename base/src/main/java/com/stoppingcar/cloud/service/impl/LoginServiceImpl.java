package com.stoppingcar.cloud.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stoppingcar.cloud.damain.UserInfo;
import com.stoppingcar.cloud.damain.enums.LoginConstant;
import com.stoppingcar.cloud.dto.RegisteredDTO;
import com.stoppingcar.cloud.dto.LoginDTO;
import com.stoppingcar.cloud.exception.StopException;
import com.stoppingcar.cloud.service.LoginService;
import com.stoppingcar.cloud.service.UserInfoService;
import com.stoppingcar.cloud.util.RedisUtil;
import com.stoppingcar.cloud.util.UserContext;
import com.stoppingcar.cloud.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public UserVO login(LoginDTO loginDTO) {
        //通过电话号码查询用户信息
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getPhone, loginDTO.getPhone())
                .eq(UserInfo::getDeleted, false));
        //用户不存在进行注册
        if (ObjectUtil.isNull(userInfo)){
            userInfo = new UserInfo();
            userInfo.setPhone(loginDTO.getPhone());
            String password = "123456";
            userInfo.setPassword(password);
            userInfo.setUsername(loginDTO.getPhone());
            userInfo.setRole("user");
            userInfoService.save(userInfo);
        }
        //密码验证
        boolean passwordIsTrue = StrUtil.isNotEmpty(loginDTO.getPassword()) &&
                userInfo.getPassword().equals(loginDTO.getPassword());
        //验证码验证
        String smsCode = redisUtil.get("SMS_CODE" + loginDTO.getPhone());
        boolean smsCodeIsTrue = StrUtil.isNotEmpty(loginDTO.getSmsCode()) &&
                StrUtil.isEmpty(smsCode) &&
                loginDTO.getSmsCode().equals(smsCode);
        if (smsCodeIsTrue || passwordIsTrue) {
            //删除原来的token
            List<String> allKey = redisUtil.getAllKey("TOKEN" + userInfo.getId() + "*");
            if (CollUtil.isNotEmpty(allKey)) {
                allKey.forEach(key -> redisUtil.delete(key));
                StpUtil.logout(userInfo.getId());
            }
            // 登录,重新获取token
            StpUtil.login(userInfo.getId());
            // 将用户的数据放入上下文session中
            StpUtil.getSession().set(LoginConstant.LOGIN_NAME, userInfo.getUsername());
            StpUtil.getSession().set(LoginConstant.REAL_NAME, userInfo.getUsername());
            StpUtil.getSession().set(LoginConstant.USER_ID, userInfo.getId());
            StpUtil.getSession().set(LoginConstant.ROLE, userInfo.getRole());

            String token = StpUtil.getTokenValue();
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userInfo, userVO);
            redisUtil.set("TOKEN" + userInfo.getId() + token, JSON.toJSONString(userVO), 30 * 24 * 60 * 60);
            userVO.setToken(token);
            return userVO;
        } else {
            throw new StopException("用户名或密码错误");
        }
    }

    @Override
    public Boolean logout() {
        //获取当前登录用户
        Long userId = UserContext.getUserId();
        //删除原来的token
        List<String> allKey = redisUtil.getAllKey("TOKEN" + userId + "*");
        if (CollUtil.isNotEmpty(allKey)) {
            allKey.forEach(key -> redisUtil.delete(key));
            StpUtil.logout(userId);
        }
        return true;
    }

    @Override
    public UserVO registered(RegisteredDTO dto) {
        //校验
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getPhone, dto.getPhone())
                .eq(UserInfo::getRole, dto.getRole())
                .eq(UserInfo::getDeleted, false));
        if (ObjectUtil.isNotNull(userInfo)) {
            throw new StopException("用户已存在");
        }

        //保存新用户信息
        userInfo = new UserInfo();
        userInfo.setUsername(dto.getPhone());
        userInfo.setPhone(dto.getPhone());
        userInfo.setPassword("123456");
        userInfo.setRole(dto.getRole());
        userInfoService.save(userInfo);

        //返回新用户数据
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userInfo, userVO);
        return userVO;
    }
}
