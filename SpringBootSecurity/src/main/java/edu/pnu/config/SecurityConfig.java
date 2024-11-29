package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityfilterChain(HttpSecurity http)throws Exception{
		
		// 접근 권한 설정
		http.authorizeHttpRequests(security -> security
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		//CSRF 보호 비활성화(사이트간 요청 위조)
		http.csrf(cf -> cf.disable()); 
		
		//기본 로그인 기능을 사용
		http.formLogin(form -> 
				form.loginPage("/login")
					.defaultSuccessUrl("/loginSuccess",true));
		
		http.exceptionHandling(ex -> ex.accessDeniedPage("/accessDenied"));
		
		http.logout(logout ->logout
			.invalidateHttpSession(true) //현재 브라우저와 연결된 세션 강제 종료
			.deleteCookies("JSESSIONID") //세션 아이디가 저장된 쿠키 삭제
			.logoutSuccessUrl("/login")); // 로그아웃 후 이동할 URL 지정
		
		http.headers(hr->hr.frameOptions(fo->fo.disable()));
		
		return http.build();
	} 
	
	@Bean
	PasswordEncoder encoder() {
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth)throws Exception{
//		auth.inMemoryAuthentication()
//		.withUser("user")
//		.password("{noop}1234")
//		.roles("MEMBER");
//		
//		auth.inMemoryAuthentication()
//			.withUser("manager")
//			.password("{noop}1234")
//			.roles("MANAGER");
//		
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password("{noop}1234")
//			.roles("ADMIN");
//	}
}
