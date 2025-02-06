package org.example.blogproject.config;

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
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화 (테스트용)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/about", "/worship", "/contact", "/board", "/signup", "/login").permitAll() // ✅ 누구나 접근 가능
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // ✅ 정적 리소스 접근 허용
                        .requestMatchers("/posts/**").authenticated() // ✅ 로그인한 사용자만 게시글 작성 가능
                        .anyRequest().authenticated() // ✅ 그 외 모든 요청은 인증 필요
                )
                .formLogin(login -> login
                        .loginPage("/login")  // ✅ 로그인 페이지 경로 (GET 요청)
                        .defaultSuccessUrl("/", true) // ✅ 로그인 성공 시 홈으로 이동
                        .failureUrl("/login?error=true") // ✅ 로그인 실패 시 다시 로그인 페이지
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // ✅ 로그아웃 요청 URL
                        .logoutSuccessUrl("/") // ✅ 로그아웃 성공 후 리디렉트 경로
                        .invalidateHttpSession(true) // ✅ 세션 삭제
                        .deleteCookies("JSESSIONID") // ✅ 쿠키 삭제
                        .permitAll()
                );

        return http.build();
    }
}
