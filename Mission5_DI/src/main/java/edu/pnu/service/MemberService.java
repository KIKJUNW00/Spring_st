package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao; 
	@Autowired
	private LogDao logDao;

	// 검색(Read - select)
	public List<MemberDTO> getAllMember() {
		Map<String, Object> resultMap = memberDao.getAllMember();
		logDao.insertLog(resultMap);
		
		return (List<MemberDTO>) resultMap.get("result");
	}

	// 검색(Read - select)
	public MemberDTO getMemberById(Integer id) {

		Map<String, Object> resultMap = memberDao.getMemberById(id);
		logDao.insertLog(resultMap);

		return (MemberDTO) resultMap.get("result");
	}

	// 입력(Create - insert)
	public MemberDTO addMember(@RequestBody MemberDTO dto) {

		Map<String, Object> resultMap = memberDao.addMember(dto);
		logDao.insertLog(resultMap);
		return (MemberDTO) resultMap.get("result");

	}

	// 수정 객체 정보를 파라미터로 전달하고 수정된 객체를 출력
	// 수정(Update - update)
	public int updateMember(MemberDTO dto) {

		Map<String, Object> resultMap = memberDao.updateMember(dto);
		logDao.insertLog(resultMap);
		return (int) resultMap.get("result");

	}

	// 삭제(Delete - delete)
	public int removeMember(Integer id) {

		Map<String, Object> resultMap = memberDao.removeMember(id);
		logDao.insertLog(resultMap);
		return (int) resultMap.get("result");
	}
}
