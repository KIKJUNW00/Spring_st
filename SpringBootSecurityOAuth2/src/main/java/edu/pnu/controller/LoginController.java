package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied");
	}
	
	//로그인 세션 정보 확인용 URL
	@GetMapping("/auth")
	public @ResponseBody ResponseEntity<?> auth(@AuthenticationPrincipal User user){
		if (user == null) {
			return ResponseEntity.ok("로그인 상태가 아닙니다.");
		}
		return ResponseEntity.ok(user);
	}
	
	//로그인 세션 정보 확인용 URL
	@GetMapping("/oauth")
	public @ResponseBody String auth(@AuthenticationPrincipal OAuth2User user) {
		if(user == null) return "OAuth2:null";
		
		//자동 회원가입을 한다면 이용할 정보 확인
		System.out.println("attributes:" + user.getAttributes());
		
		return "OAuth2:" + user;
	}
	
	//회원가입
	@GetMapping("/join")
	public void join() {
		
	}
	
	@PostMapping("/join")
	public String joinProc(Member member) {
		
		memberService.save(member);
		return "welcome";
	}
}



















