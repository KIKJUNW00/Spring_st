package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	private MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 검색(Read - select)
		@GetMapping("/members")
		public List<MemberDTO> getAllMember() {
			return memberService.getAllMember();
		}

		// 검색(Read - select)
//		@GetMapping("/member")
//		public MemberVO getMemberById(Integer id) {
//			
//			return memberService.getMemberById(id);
//		}
//		
		//입력(Create - insert)
			@PostMapping("/member")
			public MemberDTO addMember(@RequestBody MemberDTO memberVO) {
//				public MemberVO addMember( MemberVO memberVO) {
				
				return memberService.addMember(memberVO);
				
			}
		
//		//수정(Update - update)
//			@PutMapping("/member")
//			public int updateMember(MemberVO memberVO) {
//				return memberService.updateMember(memberVO);
//			}
//			
//			//삭제(Delete - delete)
//			@DeleteMapping("/member")
//			public int removeMember(Integer id) {
//				
//				return memberService.removeMember(id);
//			}
}
