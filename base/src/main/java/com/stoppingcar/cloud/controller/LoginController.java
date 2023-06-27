package com.stoppingcar.cloud.controller;

import com.stoppingcar.cloud.damain.base.Result;
import com.stoppingcar.cloud.dto.LoginDTO;
import com.stoppingcar.cloud.dto.RegisteredDTO;
import com.stoppingcar.cloud.service.LoginService;
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
@RequestMapping("/auth")
@Api(tags = "登录鉴权")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result<UserVO> login(@RequestBody LoginDTO loginDTO){
        return Result.success(loginService.login(loginDTO));
    }

    @GetMapping("/logout")
    @ApiOperation(value = "注销登录")
    public Result<Boolean> logout(){
        return Result.success(loginService.logout());
    }

    @PostMapping("/registered")
    @ApiOperation(value = "注册")
    public Result<UserVO> registered(@RequestBody RegisteredDTO dto){
        return Result.success(loginService.registered(dto));
    }
}
