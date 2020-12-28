package com.qiangqiang.security.distributed.uaa.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@EnableAuthorizationServer  //标识为一个授权服务
@Configuration
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    public AuthorizationServer() {
        super();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    //配置客户端详情服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //使用内存
        clients.inMemory()
                .withClient("c1")   //client_id0
        .secret(new BCryptPasswordEncoder().encode("secret"))   //客户端秘钥
        .resourceIds("res1","res2")    //可以访问的资源列表
        .authorizedGrantTypes(
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }
}
