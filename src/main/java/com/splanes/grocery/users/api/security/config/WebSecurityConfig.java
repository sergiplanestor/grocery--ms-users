package com.splanes.grocery.users.api.security.config;

import com.splanes.grocery.users.api.security.filter.ApiAuthFilterErrorHandler;
import com.splanes.grocery.users.api.security.filter.ApiAuthFilter;
import com.splanes.grocery.users.domain.security.jwt.TokenJwtHelper;
import com.splanes.grocery.users.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()

                .csrf()
                .disable()
                .exceptionHandling().authenticationEntryPoint(new ApiAuthFilterErrorHandler())
                .and()

                .antMatcher("/swagger-ui/**")
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()

                .antMatcher("/v3/**")
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()

                .antMatcher("/auth/**")
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()

                .antMatcher("/user/**")
                .addFilter(new ApiAuthFilter(authenticationManager(), new TokenJwtHelper(), userService))
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
