package com.hf.game.shiro.config.configparems;

import com.hf.game.shiro.config.ShiroConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by 123 on 2019-6-8.
 */
//@Configuration
//@AutoConfigureBefore(ShiroConfig.class)
public class ShiroCasParems {

    public static String casServerUrlPrefix;
    // Cas登录页面地址
    public static  String casLoginUri;
    // Cas登出页面地址
    public static String casLogoutUri;
    // 当前工程对外提供的服务地址
    public static String shiroServerUrlPrefix;
    public static String casFilterUrlPattern;
    // 登录成功地址
    public static String loginSuccessUrl;

    // 权限认证失败跳转地址
    public static String unauthorizedUrl;

    @Value("${cas.server.url.prefix}")
    public void setCasServerUrlPrefix(String casServerUrlPrefix) {
        ShiroCasParems.casServerUrlPrefix = casServerUrlPrefix;
    }

    @Value("${cas.login.uri}")
    public void setCasLoginUri(String casLoginUri) {
        ShiroCasParems.casLoginUri = casLoginUri;
    }

    @Value("${cas.logout.uri}")
    public void setCasLogoutUri(String casLogoutUri) {
        ShiroCasParems.casLogoutUri = casLogoutUri;
    }

    @Value("${shiro.server.url.prefix}")
    public void setShiroServerUrlPrefix(String shiroServerUrlPrefix) {
        ShiroCasParems.shiroServerUrlPrefix = shiroServerUrlPrefix;
    }

    @Value("${cas.filter.url.pattern}")
    public void setCasFilterUrlPattern(String casFilterUrlPattern) {
        ShiroCasParems.casFilterUrlPattern = casFilterUrlPattern;
    }

    @Value("${login.success.url}")
    public void setLoginSuccessUrl(String loginSuccessUrl) {
        ShiroCasParems.loginSuccessUrl = loginSuccessUrl;
    }

    @Value("${unauthorized.url}")
    public void setUnauthorizedUrl(String unauthorizedUrl) {
        ShiroCasParems.unauthorizedUrl = unauthorizedUrl;
    }

}
