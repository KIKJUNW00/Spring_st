package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import edu.pnu.fiter.JWTAuthenticationFilter;
import edu.pnu.fiter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private AuthenticationConfiguration authenticationConfiguraion;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
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
		
		return http.build();
		
	}
	
		
	
	
}








