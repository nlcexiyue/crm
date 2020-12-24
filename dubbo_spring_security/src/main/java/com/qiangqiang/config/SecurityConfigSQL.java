package com.qiangqiang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/24
 * \* Time: 14:17
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
public class SecurityConfigSQL extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    //自定义配置登录用户,可查询出用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //注入的加密实现类,不写的话上面的那个BCryptPasswordEncoder类会报错找不到
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //自定义的验证页面
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")    //登录页面设置
                .loginProcessingUrl("/login")   //登录访问的路径
                .defaultSuccessUrl("/main")     //登录成功后,跳转路径
                .permitAll()
                .and().authorizeRequests()      //开启登录选择认证
                .antMatchers("/public/**").permitAll()  //设置哪些路径不需要认证
                .anyRequest().authenticated()         //表示剩余其他接口,登录之后就能访问
                .and().csrf().disable();                 //关闭csrf防护


    }


}