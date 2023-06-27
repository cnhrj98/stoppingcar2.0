package com.stoppingcar.cloud.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.stoppingcar.cloud.damain.base.Result;
import com.stoppingcar.cloud.damain.enums.SaTokenConstant;
import com.stoppingcar.cloud.exception.StopException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@RestControllerAdvice
@Slf4j
public class WebConfigFilter {

    /**
     * 全局自定义异常处理
     *
     * @param exception 权限校验异常
     * @return 结果集
     */
    @ExceptionHandler(StopException.class)
    @ResponseBody
    public Result<String> exceptionHandler(HttpServletRequest exception, StopException e) {
        // 打印日志
        log.error(e.getMessage(), exception);
        return Result.fail(500, e.getMessage());
    }
}
