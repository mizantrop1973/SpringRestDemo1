package com.java.service.security.config;

import com.java.service.security.filters.TokenAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@ComponentScan("com.java.service")
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private TokenAuthFilter tokenAuthFilter;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers( "/users/**").hasAuthority("USER")
                        .requestMatchers("/login").permitAll()

                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)

                .sessionManagement((sm)->sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .getSharedObject(AuthenticationManagerBuilder.class)
                                .authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)


               //.httpBasic(withDefaults())


                /*.formLogin((form) -> form
                        .usernameParameter("login")
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                )
                .rememberMe((rm) -> rm
                        .rememberMeParameter("remember_me")
                        .tokenRepository(tokenRepositary())

                )*/




        ;
        return http.build();
    }


}
