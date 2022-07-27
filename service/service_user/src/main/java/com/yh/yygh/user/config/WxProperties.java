package com.yh.yygh.user.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "wx.open")
public class WxProperties {
    private String app_id;
    private String app_secret;
    private String redirect_url;
    @Value("${yygh.baseUrl}")
    private String baseUrl;
}
