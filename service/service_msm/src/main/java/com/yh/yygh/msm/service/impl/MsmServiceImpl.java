package com.yh.yygh.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yh.yygh.common.vo.MsmVo;
import com.yh.yygh.msm.service.MsmService;
import com.yh.yygh.msm.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.yh.yygh.msm.utils.ConstantPropertiesUtils;
import java.util.HashMap;
import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean send(String phone, String code) {
        //判断手机号是否为空
        if(StringUtils.isEmpty(phone)){
            return false;
        }
        //整合阿里云短信服务
        //设置相关参数
        DefaultProfile profile = DefaultProfile.getProfile(ConstantPropertiesUtils.REGION_Id,
                        ConstantPropertiesUtils.ACCESS_KEY_ID,
                        ConstantPropertiesUtils.SECRECT);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //签名名称
        request.putQueryParameter("SignName", "我的谷粒在线教育网站");
        //模板code
        request.putQueryParameter("TemplateCode", "SMS_180051135");
        //验证码  使用json格式   {"code":"123456"}
        Map<String,Object> param = new HashMap();
        param.put("code",code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        //调用方法进行短信发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 给远程第三方短信接口发送请求把验证码发送到用户手机上
     * @param host      //短信接口调用的URL地址
     * @param path      //具体发短信功能的地址
     * @param method    //请求方式
     * @param phoneNum  //接收验证码的手机
     * @param appcode   //用来调用第三方短信API 的AppCode
     * @param tpl_id    //模板id,联系客服人员申请成功的模板ID
     * @return     返回调用结果是否成功以及失败
     */
    public Map<String,Object> sendShortMessage(String host,String path,String method,String phoneNum, String appcode,String tpl_id){
        Map<String,Object> res = new HashMap<>();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        //封装其他的参数
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        //生成验证码
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < 6;i++){
            int random = (int)(Math.random()*10);  //随机生成1-10的数
            sb.append(random);
        }
        String code = sb.toString();
        //收短信的手机
        bodys.put("phone_number", phoneNum);
        //要发送的验证码
        bodys.put("content", "code:"+code);
        res.put("code",code);
        bodys.put("template_id", tpl_id);
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            StatusLine statusLine = response.getStatusLine();
            //状态码
            int statusCode = statusLine.getStatusCode();
            if(statusCode==200){
                res.put("isSend",true);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.put("isSend",false);
        return res;
    }

    @Override
    public boolean send(MsmVo msmVo) {
        if(!StringUtils.isEmpty(msmVo.getPhone())){
            String code = (String)msmVo.getParam().get("code");
            return this.send(msmVo.getPhone(),code);
        }
        return false;
    }


}
