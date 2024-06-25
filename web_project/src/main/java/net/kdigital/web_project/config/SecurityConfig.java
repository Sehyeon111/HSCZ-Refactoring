package net.kdigital.web_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.handler.CustomFailureHandler;
import net.kdigital.web_project.handler.CustomSuccessHandler;

@Configuration // SecurityConfig 클래스가 설정 클래스임을 나타내는 Annotation
@EnableWebSecurity // 웹 보안은 모두 여기서
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomFailureHandler failureHandler;
	private final CustomSuccessHandler successHandler;

	// 예외처리할 url 설정
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/favicon.ico", "/error");
	};

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 웹 요청에 대한 접근권한 설정
		http
				.authorizeHttpRequests((auth) -> auth.requestMatchers(
						"/", "/user/join", "/user/login", "/user/joinProc", "/user/loginProc", "/user/ccaCheck",
						"/user/confirmId", "/trade/showStat", "/trade/stringChart", "/trade/pieChart",
						"/trade/xyCluster", "/trade/StackCluster", "/trade/Exbarchart", "/trade/Ixbarchart",
						"/assets/**", "/images/**", "/script/**", "/chart/**", "/info/**", "/subhead/**", "/predict/**",
						"/heading/**", "/news/**", "/cca/boardList", "/cca/detail", "/cca/delete", "/cca/update",
						"/cca/replyWrite", "/cca/replyUpdate", "/cca/replyDelete", "/cca/replyAll", "/cca/ccaList",
						"/cca/ccaList", "/cca/ccaList", "/exchangeRate"

				).permitAll()
						.anyRequest().authenticated());

		// Custom Login 설정
		http
				.formLogin((auth) -> auth
						.loginPage("/user/login")
						.failureHandler(failureHandler)
						.successHandler(successHandler)
						.usernameParameter("userId")
						.passwordParameter("userPwd")
						.loginProcessingUrl("/user/loginProc"));
		// .defaultSuccessUrl("/").permitAll()); // true 설정

		// 로그아웃 설정
		http
				.logout((auth) -> auth
						.logoutUrl("/user/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID"));

		http
				.csrf((auth) -> auth.disable());

		return http.build();
	};

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	};

}
