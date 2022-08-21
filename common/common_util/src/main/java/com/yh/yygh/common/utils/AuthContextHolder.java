package com.yh.yygh.common.utils;

import com.yh.yygh.common.helper.JwtHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

//获取当前用户信息工具类
public class AuthContextHolder {

    public static void testGit(){
        System.out.println("....");
    }
    //获取当前用户id
    public static Long getUserId(HttpServletRequest request){
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取userid
        Long userId = JwtHelper.getUserId(token);
        return userId;
    }
    //获取当前用户名称
    public static String getUserName(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取userid
        String userName = JwtHelper.getUserName(token);
        return userName;
    }
}
