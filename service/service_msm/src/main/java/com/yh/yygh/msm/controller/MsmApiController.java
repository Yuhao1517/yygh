package com.yh.yygh.msm.controller;

import com.yh.yygh.common.result.Result;
import com.yh.yygh.msm.service.MsmService;
import com.yh.yygh.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/msm")
public class MsmApiController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = RandomUtil.getSixBitRandom();
        //调用service方法，通过整合短信服务进行发送
        boolean isSend = msmService.send(phone,code);
        //生成验证码放到redis里面，设置有效时间
        if(isSend) {
            redisTemplate.opsForValue().set(phone,code,1, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.fail().message("发送短信失败");
        }
    }

    @GetMapping("sendShortMessage/{phone}")
    public Result sendShortMessage(@PathVariable String phone){
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "0dc79a2366234cd5863d4a201a785c7d";
        String tpl_id = "TPL_0000";
        Map<String, Object> map = msmService.sendShortMessage(host, path, method, phone, appcode, tpl_id);
        //判断短信发送结果
        if((boolean) map.get("isSend")){
            //如果发送成功，则将验证码存入Redis
            String code = (String) map.get("code");
            redisTemplate.opsForValue().set(phone,code,1, TimeUnit.MINUTES);
            return Result.ok();
        }
        return Result.fail().message("发送短信失败");
    }

}
