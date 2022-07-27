package com.yh.yygh.hosp.service;

import com.yh.yygh.common.vo.SignInfoVo;
import com.yh.yygh.hosp.pojo.HospitalSet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 医院设置表 服务类
 * </p>
 *
 * @author yh
 * @since 2022-05-26
 */
public interface HospitalSetService extends IService<HospitalSet> {

    String getSignKey(String hoscode);

    SignInfoVo getSignInfoVo(String hoscode);
}
