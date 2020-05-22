package com.pelime.eplatform.security;

import com.pelime.xtools.sysservice.EbanckUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.endpoint.RedirectResolver;


@Configuration
@EnableAuthorizationServer
public class MyAuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;

//    @Override
////    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
////        oauthServer.tokenKeyAccess("permitAll()")
////                .checkTokenAccess("permitAll()")
////                .allowFormAuthenticationForClients();
////    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        System.out.println("密码："+bCryptPasswordEncoder.encode("123456"));
        clients.inMemory()
                .withClient("app")
                .secret("{noop}123456")
                .authorizedGrantTypes("authorization_code","password","refresh_token","client_credentials")
                .scopes("all");
               // .autoApprove(true)
    }

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager);
//        //endpoints.userDetailsService(ebanckUserDetailService);  //refresh_token 时获取用户信息
// //       endpoints.redirectResolver(new RedirectResolver() {
////            @Override
////            public String resolveRedirect(String s, ClientDetails clientDetails) throws OAuth2Exception {
////                return s;
////            }
//    //});
//    }
}
