package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
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
//		public MemberDTO getMemberById(Integer id){
//			
//			return null;
//		}
		
		//입력(Create - insert)
		public MemberDTO addMember(@RequestBody MemberDTO dto) {
			
			return memberDao.addMember(dto);
			
		}
		
//		//수정 객체 정보를 파라미터로 전달하고 수정된 객체를 출력
//		//수정(Update - update)
//		public int updateMember(MemberDTO memberVO) {
//			MemberDTO m = getMemberById(memberVO.getId());
//			if(m == null)
//				return 0;
//			m.setName(memberVO.getName());
//			m.setPass(memberVO.getPass());
//			return 1;
//		}
//		
//		//삭제(Delete - delete)
//		public int removeMember(Integer id) {
//			try {
//				memberList.remove(getMemberById(id));
//			} catch (Exception e) {
//				return 0;
//			}
//			return 1;
//		}
}
