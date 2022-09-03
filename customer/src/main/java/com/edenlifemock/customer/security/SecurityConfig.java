package com.edenlifemock.customer.security;

import com.edenlifemock.customer.security.filters.ExceptionFilter;
import com.edenlifemock.customer.security.filters.JwtFilter;
import com.edenlifemock.customer.security.filters.UsernameAndPasswordFilter;
import com.edenlifemock.customer.security.handlers.CustomAccessDeniedHandler;
import com.edenlifemock.customer.security.jwt.JwtConfig;
import com.edenlifemock.customer.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.crypto.SecretKey;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new UsernameAndPasswordFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterBefore(exceptionFilter(), UsernameAndPasswordFilter.class)
                .addFilterAfter(new JwtFilter(secretKey, jwtConfig), UsernameAndPasswordFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/user/**")
                .permitAll()
                .anyRequest().authenticated();
    }
    @Bean
    public ExceptionFilter exceptionFilter() {
        return new ExceptionFilter();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

}
