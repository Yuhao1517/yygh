package com.yh.yygh.msm.service;

import com.yh.yygh.common.vo.MsmVo;

import java.util.Map;

public interface MsmService {
    //发送手机验证码
    boolean send(String phone, String code);

    Map<String, Object> sendShortMessage(String host, String path, String method, String phoneNum, String appcode, String tpl_id);


    boolean send(MsmVo msmVo);

}

