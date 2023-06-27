package com.stoppingcar.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stoppingcar.cloud.damain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
