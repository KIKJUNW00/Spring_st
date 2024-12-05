package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import edu.pnu.OAuth2SuccessHandler;
import edu.pnu.fiter.JWTAuthenticationFilter;
import edu.pnu.fiter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final OAuth2SuccessHandler successHandler;

	private final AuthenticationConfiguration authenticationConfiguraion;

	private final MemberRepository memberRepository;
	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf -> csrf.disable()); //CSRF 보호 비활성화
		
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		//Form을 이용한 로그인을 사용하지 않겠다는 설정
		http.formLogin(frmLogin -> frmLogin.disable());
		
//		Http Basic인증 방식을 사용하지 않겠다는 설정
		http.httpBasic(basic -> basic.disable());
		
		//세션을 유지하지 않겠다고 설정
		//URL 호출 뒤 응답할 때 까지는 유지되지만 응답 후 삭제된다는 의미
		http.sessionManagement(sm -> sm
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguraion.getAuthenticationManager()));
		
		//스프링 시큐리티가 등록한 필터들 중에서 AuthorizationFilte 앞에 앞에서 작성한 필터를 삽입한다.
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		
		//로그인에 성공하면 임의의 사용자를 생성해서 DB에 저장하고 JWT토큰을 만들어서 응답 헤더에 설정하는 핸들러
		http.oauth2Login(oauth2->oauth2.successHandler(successHandler));

		
		return http.build();
		
	}
	
		
	
	
}








