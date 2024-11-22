package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;


@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	public List<Member> getMembers(){
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public Member getMemberById(Integer id) {
		return memberService.getMemberById(id);
	}
	
	//insert
	@PostMapping("/member")
	public Member postMember(Member member) {
		return memberService.postMember(member);
	}
	
	//update
	@PutMapping("/member")
	public Member putMember(Member member) {
		return memberService.putMember(member);
	}
	
	//delete
	@DeleteMapping("/member/{id}")
	public Member deleteMember(@PathVariable Integer id) {
		return memberService.deleteMember(id);
	}
}









