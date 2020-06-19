package com.dev.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserDetailsService userDetailsService,
                             PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/cinema-halls").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/movie-sessions").hasRole("ADMIN")
                .antMatchers("/shopping-carts/**").hasRole("USER")
                .antMatchers("/orders/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/movies").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls").permitAll()
                .antMatchers(HttpMethod.GET, "/movie-sessions/available").permitAll()
                .antMatchers(HttpMethod.GET, "/users/by-email").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
