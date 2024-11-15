package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

@Service
public class MemberService {
private final MemberDao memberDao = new MemberDao();
	
	//검색(Read - select)
		public List<MemberDTO> getAllMember(){
			return memberDao.getAllMember();
		}
		
		//검색(Read - select)
		public MemberDTO getMemberById(MemberDTO dto){

			return memberDao.getMemberById(dto);
		}
		
		//입력(Create - insert)
		public MemberDTO addMember(@RequestBody MemberDTO dto) {
			
			return memberDao.addMember(dto);
			
		}
		
		//수정 객체 정보를 파라미터로 전달하고 수정된 객체를 출력
		//수정(Update - update)
		public int updateMember(MemberDTO dto) {
			
			return memberDao.updateMember(dto);
		
		}
		
		//삭제(Delete - delete)
		public int removeMember(MemberDTO dto) {
			
			return memberDao.removeMember(dto);
		}
}
