package com.aneeque.coding.challenge.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
@EnableCaching
public class CodingChallengeApplication {

    public static void main(String[] args) {

        SpringApplication.run(CodingChallengeApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder (){

        return  new BCryptPasswordEncoder();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }


}
