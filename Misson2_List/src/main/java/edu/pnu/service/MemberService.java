package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
private List<MemberVO> memberList = new ArrayList<>();
	
	public MemberService() {
		for (int i = 1; i <= 10; i++) {
			memberList.add(MemberVO.builder()
					  .id(i).name("name" + i)
					  .pass("pass" + i)
					  .regidate(new Date()).build());
			
		}
	}
	
	//검색(Read - select)
	public List<MemberVO> getAllMember(){
		return memberList;
	}
	
	//검색(Read - select)
	public MemberVO getMemberById(Integer id){
		for (MemberVO m : memberList) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	//member 정보를 파라미터로 전달하고 추가된 객체를 출력
	//입력(Create - insert)
	public MemberVO addMember(@RequestBody MemberVO memberVO) {
		if (getMemberById(memberVO.getId()) != null) {
			System.out.println(memberVO.getId() + "가 이미 존재합니다.");
			
			return null;
		}
		memberVO.setRegidate(new Date());
		memberList.add(memberVO);
		return memberVO;
		
	}
	
	//수정 객체 정보를 파라미터로 전달하고 수정된 객체를 출력
	//수정(Update - update)
	public int updateMember(MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if(m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	
	//삭제(Delete - delete)
	public int removeMember(Integer id) {
		try {
			memberList.remove(getMemberById(id));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
