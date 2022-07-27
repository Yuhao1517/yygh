package com.yh.yygh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.yygh.common.vo.Patient;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 就诊人表 Mapper 接口
 * </p>
 *
 * @author yh
 * @since 2022-07-24
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

}
