package com.movie.config;

import com.movie.domain.User;
import com.movie.mapper.UserMapper;
import com.movie.service.CustomOAuth2UserService;
import com.movie.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpSession;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final UserMapper userMapper;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, UserMapper userMapper) {
        this.customUserDetailsService = customUserDetailsService;
        this.userMapper = userMapper;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSession session, CustomOAuth2UserService customOAuth2UserService) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/event/**", "/user/**", "/mypage/**", "/css/**", "/images/**", "/js/**", "/fonts/**", "/api/auth/**", "/test/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/")
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(authenticationSuccessHandler(session, userMapper)) // 공통 핸들러 사용
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .successHandler(authenticationSuccessHandler(session, userMapper)) // 공통 핸들러 사용
                )
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(HttpSession session, UserMapper userMapper) {
        return (request, response, authentication) -> {
            Object principal = authentication.getPrincipal();
            User user;

            if (principal instanceof OAuth2User) {
                // 소셜 로그인 처리
                OAuth2User oAuth2User = (OAuth2User) principal;
                String email = oAuth2User.getAttribute("email");

                user = userMapper.findByEmail(email)
                        .orElseThrow(() -> new IllegalStateException("소셜 사용자 정보를 찾을 수 없습니다."));
                System.out.println("소셜 로그인 사용자: " + user);

            } else {
                // 일반 로그인 처리
                String username = authentication.getName();
                user = userMapper.findByEmail(username)
                        .orElseThrow(() -> new IllegalStateException("일반 사용자 정보를 찾을 수 없습니다."));
                System.out.println("일반 로그인 사용자: " + user);
            }

            // 세션에 사용자 정보 저장
            session.setAttribute("USER", user);
            System.out.println("세션에 사용자 정보 저장: " + user);

            response.sendRedirect("/");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OAuth2UserService<org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest, OAuth2User> customOAuth2UserServiceBean() {
        return new CustomOAuth2UserService(userMapper);
    }


}