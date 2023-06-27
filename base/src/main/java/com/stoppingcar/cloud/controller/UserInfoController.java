package com.stoppingcar.cloud.controller;

import com.stoppingcar.cloud.damain.base.Result;
import com.stoppingcar.cloud.dto.UpdatePasswordDTO;
import com.stoppingcar.cloud.service.UserInfoService;
import com.stoppingcar.cloud.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@RestController
@RequestMapping("/userInfo")
@Api(tags = "用户模块")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/getUser")
    @ApiOperation(value = "通过用户名获取用户信息")
    public Result<UserVO> getUser(String username){
        return Result.success(userInfoService.getUserInfo(username));
    }

    @GetMapping("getNowUser")
    @ApiOperation(value = "获取当前登录用户")
    public Result<UserVO> getNowUser(){
        return Result.success(userInfoService.getNowUser());
    }

    @PostMapping("/updatePassword")
    @ApiOperation("修改密码")
    public Result<Boolean> updatePassword(@RequestBody UpdatePasswordDTO dto) {
        return Result.success(userInfoService.updatePassword(dto));
    }
}
