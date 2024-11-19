package edu.pnu.dao;

import java.util.ArrayList;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Vector;

import org.springframework.stereotype.Repository;

import edu.pnu.connection.JDBCconnect;
import edu.pnu.domain.MemberDTO;

@Repository
public class MemberDao extends JDBCconnect{
	Map<String, Object> resultMap = new HashMap<String, Object>();
	
//	검색
	public Map<String, Object> getAllMember(){
		int result = 0;
		
		resultMap.put("method", "get");
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		//쿼리문 작성
		try {
			String query = "SELECT * FROM member";
			psmt = con.prepareStatement(query); //statement 객체생성
			rs = psmt.executeQuery(); //쿼리 실행
			
			resultMap.put("sqlstring", psmt.toString().split(" ", 2)[1]);
			
			while (rs.next()) { //목로 안의 파일 수만큼 반복
				// DTO에 저장
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
				memberList.add(dto);
			}
			
		} catch (Exception e) {
			
			System.out.println("SELECT 시 예외 발생");
			e.printStackTrace();
		}
		
		resultMap.put("success", (result != 0));
		resultMap.put("result", memberList);
		return resultMap; //목록 반환
	}
	
	//검색-아이디
	public Map<String, Object> getMemberById(Integer id) {
		MemberDTO dto = new MemberDTO();
		
		//쿼리문 작성
		String query = "SELECT * FROM member WHERE id = ?";
		
		resultMap.put("method", "get");
		
		
		try {
			//쿼리 실행
			psmt = con.prepareStatement(query); //동적 쿼리문 준비
			psmt.setString(1, String.valueOf(id)); //쿼리문의 첫 번째 인파라미터에 값 설정
			rs = psmt.executeQuery(); //쿼리문 실행
			
			resultMap.put("sqlstring", psmt.toString().split(" ", 2)[1]);
			//결과처리
			if(rs.next()) {
				dto.setId(rs.getInt("id"));
				System.out.println(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
				
				resultMap.put("success", true);
				resultMap.put("result", dto);
			} else {
				resultMap.put("success", false);
				resultMap.put("result", null);
				
			}
			
			
		} catch (Exception e) {
					
			e.printStackTrace();
		}
		return resultMap;
	}
	
//	입력
	public  Map<String, Object> addMember(MemberDTO dto) {
		int result = 0;
		
		resultMap.put("method", "post");
		
		
		try {
			String query = "INSERT INTO member(pass, name) VALUES (?,?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPass());
			psmt.executeUpdate();
			resultMap.put("sqlstring", psmt.toString().split(" ", 2)[1]);
			
			
			
		} catch (Exception e) {
			
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		resultMap.put("success", (result != 0));
		resultMap.put("result", dto);
		
		return resultMap;
	}
	
	
	//수정
		public Map<String, Object> updateMember(MemberDTO dto) {
			int result = 0;
			
			resultMap.put("method", "put");
			
			try {
				String query = "UPDATE member SET pass = ?, name = ? WHERE id = ? ";
				
				
				psmt = con.prepareStatement(query);
				
				// 각 쿼리의 파라미터에 dto에서 값을 설정
				psmt.setString(1, dto.getPass()); //쿼리문의 첫 번째 인파라미터에 값 설정
				psmt.setString(2, dto.getName());
				psmt.setInt(3, dto.getId());
				
				// 쿼리 실행 및 업데이트된 행 수 반환
				result = psmt.executeUpdate();
				resultMap.put("sqlstring", psmt.toString().split(" ", 2)[1]);
				
				
			} catch (Exception e) {
				
				System.out.println("UPDATE 중 예외 발생");
				e.printStackTrace();
			}
			if (result==1)
				System.out.println("멤버 정보 수정 성공");

			resultMap.put("success", (result != 0));
			resultMap.put("result", result);
			return resultMap;
		}
		
		//삭제
		public Map<String, Object> removeMember(Integer id) {
			int result = 0;
			resultMap.put("method", "delete");
			
			String query = "DELETE FROM member WHERE id = ? ";
			try {
				
				psmt = con.prepareStatement(query);
				
				psmt.setInt(1, id);
				result = psmt.executeUpdate();
				
				resultMap.put("sqlstring", psmt.toString().split(" ", 2)[1]);
				
			} catch (Exception e) {
				
				System.out.println("DELET 중 예외 발생");
				e.printStackTrace();
			}
			if (result==1) 
				System.out.println("멤버 삭제 성공");
			
			else 
				System.out.println("멤버 삭제 실패");
			
			resultMap.put("success", (result != 0));
			resultMap.put("result", result);
					
			return resultMap;
		}
}
