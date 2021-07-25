package com.aneeque.coding.challenge.demo.Config.SpringSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//
//    @Override
//    // configure authentication
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.userDetailsService(userDetailsService)
//              .passwordEncoder(bCryptPasswordEncoder());
//
//    }

    // configure authorization

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll();

    }

}
