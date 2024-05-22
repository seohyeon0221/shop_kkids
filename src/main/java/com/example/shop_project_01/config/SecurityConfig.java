package com.example.shop_project_01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        request -> request.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()

                                .requestMatchers("/mypage/**","/cart/**").authenticated()
                               .requestMatchers("/admin/**").hasRole("ADMIN")
                               .requestMatchers("/product_detail/cart_and_buy").authenticated()
                                .anyRequest().permitAll()

                )

                .formLogin(
                        form -> form
                                .loginPage("/user/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/",true)
                                .permitAll()
//                            form -> form.loginPage("/user/login")
//                                   .loginProcessingUrl("/user/login")
//                                   .defaultSuccessUrl("/")
                )
                .logout(
                        out -> out.logoutSuccessUrl("/")
                                .logoutUrl("/logout"))
                .csrf(csrf->csrf.disable()

                );
        return http.build();
    }
}