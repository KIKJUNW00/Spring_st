package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class MemberController {
	private MemberService ms;

	public MemberController(MemberService ms) {
		this.ms = ms;
	}
	
	//검색
	@GetMapping("/members")
	public List<MemberDTO> getAllMember(){
		return ms.getAllMember();
	}
	
	//아이디 검색
	@GetMapping("/member")
	public MemberDTO getMemberById(MemberDTO dto) {
		return ms.getMemberById(dto);
	}
	
	//입력-삽입
	@PostMapping("/member")
	public MemberDTO postMethodName(MemberDTO dto) {
		
		return ms.addMember(dto);
	}
	
	//수정
	@PutMapping("/member")
	public int updateMember(MemberDTO dto) {
		
		return ms.updateMember(dto);
	}
	
	//삭제
	@DeleteMapping("member")
	public int removeMember(MemberDTO dto) {
		return ms.removeMember(dto);
	}
}











