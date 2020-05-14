package com.pelime.xtools.config;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfig {
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    BasicPasswordEncryptor encryptor(){
//        return
//    }
    @Bean
    public BasicPasswordEncryptor basicPasswordEncryptor(){
        return new BasicPasswordEncryptor();
    }
}
