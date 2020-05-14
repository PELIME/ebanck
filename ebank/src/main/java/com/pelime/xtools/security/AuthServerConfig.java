package com.pelime.xtools.security;

import com.pelime.xtools.sysservice.EbanckUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    EbanckUserDetailService ebanckUserDetailService;
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        System.out.println(passwordEncoder.encode("123456"));
        clients.inMemory()
                .withClient("app_123456")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("authorization_code","password","refresh_token","client_credentials")
                .scopes("user_info")
                .autoApprove(true)
        // .redirectUris("http://localhost:10803/login","http://localhost:8085/login")
        //.redirectUris("http://localhost:8082/ui/login","http://localhost:8083/ui2/login","http://localhost:8082/auth/login")
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(ebanckUserDetailService);  //refresh_token 时获取用户信息
        endpoints.redirectResolver(new RedirectResolver() {
            @Override
            public String resolveRedirect(String s, ClientDetails clientDetails) throws OAuth2Exception {
                return s;
            }
        });
    }
}
