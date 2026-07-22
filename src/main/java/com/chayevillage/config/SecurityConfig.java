package com.chayevillage.config;

import com.chayevillage.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            // 公开接口 - 读取操作无需认证
            .antMatchers(HttpMethod.GET, "/api/v1/banners/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/village-info/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/spots/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/articles/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/dashboard/stats/**").permitAll()
            // 管理员登录接口公开
            .antMatchers("/api/v1/admin/auth/login").permitAll()
            // 管理端其他接口需要认证
            .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
