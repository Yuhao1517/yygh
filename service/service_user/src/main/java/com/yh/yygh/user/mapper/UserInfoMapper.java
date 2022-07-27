package com.yh.yygh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.yygh.user.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yh
 * @since 2022-07-17
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
