package com.stoppingcar.cloud.damain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
public class SaTokenConstant {
    /**
     * token的字段名
     */
    public static final String TOKEN_NAME = "token";

    /**
     * token无操作存活时间(指定时间内无操作就视为token过期) 单位：秒
     */
    public static final long ACTIVITY_TIMEOUT = 30 * 24 * 60 * 60;

    public static final List<String> ALL_ROUTERS = Collections.singletonList("/**");

    public static final List<String> EXCLUDE_PATH_PATTERNS = Arrays.asList(
            "/test/**",
            "/file/upload/img/head/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v2/**",
            "/**/doc.html",
            "/doc.html",
            "/**/swagger-ui.html",
            "/swagger-ui.html/**",
            "/img/head/**",
            ExcludePathEnum.TEMP_TOKEN.getPath(),
            ExcludePathEnum.VERIFY_IMG.getPath(),
            ExcludePathEnum.LOGIN.getPath(),
            ExcludePathEnum.LOGOUT.getPath(),
            ExcludePathEnum.REGISTERED.getPath()
    );
}
