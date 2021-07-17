package org.tianly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.tianly.bean.MyRedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {
    /**
     * 使用 password 登录模式
     * 适用于前后端分离
     */
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    RedisConnectionFactory myRedisConnectionFactory;

    @Autowired
    TokenStore tokenStore;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 设置认证模式为 password
                .withClient("password")
                // 设置授权模式为 password 和 refresh_token
                // refresh_token 为 Spring Boot 中特有的授权模式
                .authorizedGrantTypes("password", "refresh_token")
                // token 的过期时间
                .accessTokenValiditySeconds(1800)
                // 设置资源 id，也就是资源的名字
                .resourceIds("rid")
                .scopes("all")
                .secret("$2a$10$3exePEMS2hwNVXzg3NRPVurMaA/ksEu5UGe6.cSctS3J7l6RsIarS");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    @Bean
    public TokenStore tokenStore(){
        MyRedisTokenStore myRedisTokenStore = new MyRedisTokenStore(myRedisConnectionFactory);
        myRedisTokenStore.setPrefix("oauth:");
        return  myRedisTokenStore;
    }
}
