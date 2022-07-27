package com.yh.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yh.yygh.common.exception.HospitalException;
import com.yh.yygh.common.result.ResultCodeEnum;
import com.yh.yygh.common.vo.SignInfoVo;
import com.yh.yygh.hosp.pojo.HospitalSet;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.yygh.hosp.mapper.HospitalSetMapper;
import com.yh.yygh.hosp.service.HospitalSetService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医院设置表 服务实现类
 * </p>
 *
 * @author yh
 * @since 2022-05-26
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper wrapper = new QueryWrapper<HospitalSet>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        if(hospitalSet!=null){
            return hospitalSet.getSignKey();
        }
        return null;
    }

    @Override
    public SignInfoVo getSignInfoVo(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        if(null == hospitalSet) {
            throw new HospitalException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        return signInfoVo;
    }
}
