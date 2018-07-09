package com.lynx.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .disable() // disable form authentication
                .anonymous()
                    .disable() // disable anonymous user
                .authorizeRequests()
                    .anyRequest()
                    .denyAll(); // denying all access

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth    .inMemoryAuthentication() // creating user in memory
                .withUser("user")
                .password(passwordEncoder.encode("password")).roles("USER")
                .and().withUser("admin")
                .password(passwordEncoder.encode("password")).authorities("ROLE_ADMIN");
    }


}
