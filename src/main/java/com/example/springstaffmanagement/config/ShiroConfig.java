package com.example.springstaffmanagement.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import ch.qos.logback.core.joran.action.NewRuleAction;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * cookie管理对象
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        System.out.println("进方法rememberMeManager");
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        System.out.println(simpleCookie);
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //记住我cookie生效时间 ,单位秒
        simpleCookie.setMaxAge(120);
        System.out.println("成功保存cookie");
        return simpleCookie;
    }
    /**
     * FormAuthenticationFilter 过滤器 过滤记住我
     * @return
     */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        // 对应 rememberMeCookie() 方法中的 name
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }

    //ShiroFilterFactoryBean
       @Bean
       public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
           ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
           //关联DefaultwebSecurityManager,设置安全管理器
           shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
           //添加内置过滤器
//        anon:无认证就可访问
//        authc:必须认证才能访问
//        user：必须拥有记住我功能才可使用
//        perms:拥有对某个资源的权限才能访问
//        role:拥有某个角色权限才能访问

           //拦截
           Map<String,String> filterChainDefinitionMap = new HashMap<String,String>();
           //要测试登录拦截跳转登录页面，先配置登录页面不拦截，和拦截其它页面需要拦截
           filterChainDefinitionMap.put("/user/login","anon");
           filterChainDefinitionMap.put("/swagger-ui.html","anon");

           //页面样式全部放开
           filterChainDefinitionMap.put("/static/**","anon");
           filterChainDefinitionMap.put("/css/**","anon");
           filterChainDefinitionMap.put("/font/**","anon");
           filterChainDefinitionMap.put("/image/**","anon");
           filterChainDefinitionMap.put("/js/**","anon");
           filterChainDefinitionMap.put("/picture/**","anon");
           filterChainDefinitionMap.put("/webfonts/**","anon");


           //被shiro拦截的swagger资源放行
           filterChainDefinitionMap.put("/doc.html/**", "anon");
           // swagger 资源基本路径
           filterChainDefinitionMap.put("/swagger-resources/**", "anon");
           filterChainDefinitionMap.put("/swagger-resources", "anon");
           filterChainDefinitionMap.put("/swagger**/**", "anon");
           // swagger2 api 接口地址
           filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
           filterChainDefinitionMap.put("/v2/**", "anon");
           filterChainDefinitionMap.put("/webjars/**", "anon");
           // swagger ui 静态页面资源路径
           filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**","anon");
           // swagger ui 选项配置请求url前缀
           filterChainDefinitionMap.put("/swagger-resources/configuration/ui/**", "anon");
           // swagger ui 安全配置请求url前缀
           filterChainDefinitionMap.put("/swagger-resources/configuration/security/**", "anon");


           filterChainDefinitionMap.put("/emps","authc");
           filterChainDefinitionMap.put("/index","authc");

           //除了以上路径全部拦截
           filterChainDefinitionMap.put("/**", "user");


           //授权，正常的情况下，没有授权会跳转指定页面
           //通过以下方式设置要增加授权的页面以及访问别名，通过realm中的方法判断用户是否有权限
           filterChainDefinitionMap.put("/emps","roles[admin]");

           //开启并使用授权
           shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

           //解决session丢失
//           Map<String, Filter> fmap = new HashMap<>();
//           fmap.put("addPrincipal", addPrincipalToSessionFilter());
//           shiroFilterFactoryBean.setFilters(fmap);

           //登录拦截
           shiroFilterFactoryBean.setLoginUrl("/user/login");
           //登录成功跳转页面
           shiroFilterFactoryBean.setSuccessUrl("/index");
           //未授权页面
           shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
           return shiroFilterFactoryBean;
       }

    //DefaultwebSecurityManager
      @Bean
      public DefaultWebSecurityManager defaultWebSecurityManager(ShiroRealm shiroRealm){
          DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
          //关联realm
          defaultWebSecurityManager.setRealm(shiroRealm);
          //关联记住我
          defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
          defaultWebSecurityManager.setSessionManager(sessionManager());
          return defaultWebSecurityManager;
      }
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 去掉shiro登录时url里的JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    //加载ShiroRealm
      @Bean
      public ShiroRealm shiroRealm(){
           return new ShiroRealm();
      }

    //整合ShiroDialect:用来整合shiro thymeleaf
      @Bean
      public ShiroDialect getShiroDialects(){
           return new ShiroDialect();
      }

    /**
     * Shiro自定义过滤器（解决session丢失）
     * @return
     */
//    @Bean
//    public OncePerRequestFilter addPrincipalToSessionFilter() {
//        return new AddPrincipalToSessionFilter();
//    }

}
