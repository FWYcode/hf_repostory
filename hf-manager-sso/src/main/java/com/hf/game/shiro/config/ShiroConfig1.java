package com.hf.game.shiro.config;

import com.hf.game.shiro.realm.MyRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 123 on 2019-6-4.
 */
@Configuration
public class ShiroConfig1 {
    @Value("${cas.server.url.prefix}")
    public String casServerUrlPrefix;
    // 当前工程对外提供的服务地址
    @Value("${shiro.server.url.prefix}")
    public String shiroServerUrlPrefix;
    @Value("${cas.filter.url.pattern}")
    public String casFilterUrlPattern;
    // Cas登录页面地址
    @Value("${cas.login.url}")
    public String casLoginUrl;
    // Cas登出页面地址
    @Value("${cas.logout.uri}")
    public String casLogoutUrl ;
    // 登录地址
    @Value("${login.url}")
    public String loginUrl;
    // 登出地址（casserver启用service跳转功能，需在webapps\cas\WEB-INF\cas.properties文件中启用cas.logout.followServiceRedirects=true）
    @Value("${logout.url}")
    public String logoutUrl;
    // 登录成功地址
    @Value("${login.success.url}")
    public String loginSuccessUrl;

    // 权限认证失败跳转地址
    @Value("${unauthorized.url}")
    public String unauthorizedUrl;

    /* 设置安全过滤器
    * @param defaultWebSecurityManager
    * @return
    */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager, CasFilter casFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /**
         * 设置shiro内置过滤器，实现相关的权限拦截
         * 常用过滤器：
         *      anon：无需认证（登录）可以访问
         *      authc：必须认证才可以访问
         *      user：使用rememberMe的功能可以直接访问
         *      perms：必须得到该资源权限才能访问
         *      role：必须得到该资源角色权限才能访问
         */

        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        Map<String, Filter> filterMap = new LinkedHashMap<>();

        filterMap.put("casFilter", casFilter);
//        filterMap.put("myfilter", new MyCasFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        shiroFilterFactoryBean.setSuccessUrl(loginSuccessUrl);
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

    private void loadShiroFilterChain(ShiroFilterFactoryBean filterFactoryBean) {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/user/**","myfilter");
        filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
        //2.不拦截的请求
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        filterChainDefinitionMap.put("/con/**", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");
        //3.拦截的请求（从本地数据库获取或者从casserver获取(webservice,http等远程方式)，看你的角色权限配置在哪里）
        filterChainDefinitionMap.put("/user", "authc"); //需要登录
        filterChainDefinitionMap.put("/user/add/**", "authc,roles[admin]"); //需要登录，且用户角色为admin
        filterChainDefinitionMap.put("/user/delete/**", "authc,perms[user:delete]"); //需要登录，且用户有权限为user:delete
        //4.登录过的不拦截
        filterChainDefinitionMap.put("/**", "user");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

    }

    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        defaultWebSecurityManager.setCacheManager(getCacheManager());
        defaultWebSecurityManager.setSubjectFactory(new CasSubjectFactory());
        return defaultWebSecurityManager;
    }

    @Bean(name = "cacheManager")
    public EhCacheManager getCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;
    }

    @Bean(name = "myRealm")
    public MyRealm getMyRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCasServerUrlPrefix(casServerUrlPrefix);
        System.out.println(shiroServerUrlPrefix);
        System.out.println(casServerUrlPrefix);
        System.out.println(loginSuccessUrl);
        System.out.println(unauthorizedUrl);
        System.out.println(casFilterUrlPattern);
        System.out.println(loginUrl);
        myRealm.setCasService(shiroServerUrlPrefix + casFilterUrlPattern);
        myRealm.setDefaultRoles("user");
        return myRealm;
    }

    @Bean(name = "casFilter")
    public CasFilter getCasFilter() {
        CasFilter casFilter = new CasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(true);
        System.out.println("12333");
        casFilter.setFailureUrl(loginUrl);
//        casFilter.setSuccessUrl(ShiroConfig.loginSuccessUrl);
        return casFilter;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator addpc = new DefaultAdvisorAutoProxyCreator();
        addpc.setProxyTargetClass(true);
        return addpc;
    }

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistrationBean.addInitParameter("targetFilterLifecycle", "true");
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
        registrationBean.setName("singleSignOutFilter");
        registrationBean.setFilter(new SingleSignOutFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setEnabled(true);
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean singleSignOutListener() {
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
        listenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        listenerRegistrationBean.setEnabled(true);
        return listenerRegistrationBean;
    }
}
