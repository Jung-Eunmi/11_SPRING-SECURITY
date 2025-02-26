package com.ohgiraffers.sessionsecurity.config;

import com.ohgiraffers.sessionsecurity.common.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
/* comment.
    @EnableWebSecurity
    spring security 기능 활성화를 위한 어노테이션 */
@EnableWebSecurity
public class SecurityConfig {

    /* comment.
    *   비밀번호를 인코딩하기 위한 Bean 생성
    *   BCrypt 객체는 비밀번호 암호화를 위해 가장 많이 사용되는 알고리즘 중 하나이다. */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /* comment.
    *   정적 리소스(static)에 대한 요청은 Security 설정이 돌지 못 하게 설정 */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Autowired
    private AuthFailHandler authFailHandler;

    /* comment. 여기가 설정의 핵심 */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // 서버의 리소스 접근 가능 권한 설정
        http.authorizeHttpRequests(auth -> {

            // permitAll() -> 인증 되지 않은(로그인 되지 않은) 사용자들이 접근할 수 있는 URL 기술
            auth.requestMatchers("/auth/login", "user/signup", "auth/fail", "/").permitAll();

            // hasAnyAuthority() -> 해당하는 URL 은 권한을 가진 사람만 접근할 수 있다.
            // /admin/* 요청은 관리자 권한을 가진 사람만 접근 할 수 있다.
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole());

            // /user/* 요청은 일반 회원 권한을 가진 사람만 접근할 수 있다.
            auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getRole());

            // 그 외 어떠한 요청들은 권한 상관 없이 들어갈 수 있다. (단, 로그인 된 인원에 한하여)
            auth.anyRequest().authenticated();
        }).formLogin(login -> {

            // 로그인 페이지 url 을 입력
            login.loginPage("/auth/login");

            // 사용자가 ID 를 입력하는 필드(input 타입 name 과 반드시 일치해야 함)
            login.usernameParameter("user");

            // 사용자가 PWD 를 입력하는 필드(input 타입 name 과 반드시 일치해야 함)
            login.passwordParameter("pass");

            // 사용자가 로그인에 성공했을 시 보내줄 URL 기술
            login.defaultSuccessUrl("/", true);

            // 사용자가 로그인에 실패했을 시 내용을 기술 한 객체 호출(아직 미작성)
            login.failureHandler(authFailHandler);
        }).logout(logout -> {

            // 로그아웃을 담당 할 핸들러 메소드 요청 URL 기술
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));

            // session 은 쿠키 방식으로 저장되어 있어 로그인을 하면
            // 해당하는 쿠키를 삭제함으로서 로그아웃을 만들어준다.
            // session 의 쿠키는 JSESSIONID 에 담겨있다.
            logout.deleteCookies("JSESSIONID");

            // 서버측의 세션 공간 만료
            logout.invalidateHttpSession(true);

            // 로그아웃 성공 시 요청 URL 기술
            logout.logoutSuccessUrl("/");
        }).sessionManagement(session -> {

            // session 의 허용 갯수 제한
            // 한 사용자가 여러 창을 띄워 동시에 세션 여러 개 활성화 방지
            session.maximumSessions(1);

            // 세션이 만료 되었을 때 요청 할 URL 기술
            session.invalidSessionUrl("/");

        // 추가적인 구현이 필요하므로 비활성 화
        }).csrf(csrf -> csrf.disable());

        // 위에서 설정 한 내용대로 Security 기능 빌드(생성)
        return http.build();
    }
}
