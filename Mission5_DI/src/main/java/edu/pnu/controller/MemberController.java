package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
public class MemberController {
	@Autowired
	private MemberService ms;
	
	//검색
	@GetMapping("/members")
	public List<MemberDTO> getAllMember(){
		return ms.getAllMember();
	}
	
	//아이디 검색
	@GetMapping("/member")
	public MemberDTO getMemberById(Integer id) {
		return ms.getMemberById(id);
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
//	public int removeMember(@RequestBody Integer id) {
		public int removeMember(Integer id) {
		return ms.removeMember(id);
	}
}











