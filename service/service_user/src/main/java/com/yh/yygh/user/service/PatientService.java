package com.yh.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.yygh.common.vo.Patient;

import java.util.List;


/**
 * <p>
 * 就诊人表 服务类
 * </p>
 *
 * @author yh
 * @since 2022-07-24
 */
public interface PatientService extends IService<Patient> {

    List<Patient> findAllUserId(Long userId);

    Patient getPatientId(Long id);
}
