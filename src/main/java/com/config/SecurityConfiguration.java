package com.config;

import com.entity.Role;
import com.service.UserDetailsServiceImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/index").hasAuthority(Role.NORMAL_USER.toString())
                .antMatchers("/modify-password").hasAuthority(Role.NORMAL_USER.toString())
                .antMatchers("/add-new-user").hasAuthority(Role.ADMINISTRATOR.toString())
                .antMatchers("/user-list-page").hasAuthority(Role.ADMINISTRATOR.toString())
                .antMatchers("/set-password").hasAuthority("INACTIVE")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/index");
    }
}
