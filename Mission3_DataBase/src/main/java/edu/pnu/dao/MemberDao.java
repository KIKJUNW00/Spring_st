package edu.pnu.dao;

import java.util.List;
import java.util.Vector;

import edu.pnu.connection.JDBCconnect;
import edu.pnu.domain.MemberDTO;

public class MemberDao extends JDBCconnect{
	
//	검색
	public List<MemberDTO> getAllMember(){
		List<MemberDTO> memberList = new Vector<MemberDTO>();
		
		//쿼리문 작성
		String query = "SELECT * FROM member";
		try {
			stmt = con.createStatement(); //statement 객체생성
			rs = stmt.executeQuery(query); //쿼리 실행
			
			while (rs.next()) { //목로 안의 파일 수만큼 반복
				// DTO에 저장
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getDate(4));
				memberList.add(dto);
			}
		} catch (Exception e) {
			System.out.println("SELECT 시 예외 발생");
			e.printStackTrace();
		}
		return memberList; //목록 반환
	}
	
	
	public MemberDTO getMemberById(Integer id) {
//	public MemberDTO getMemberById(MemberDTO dto) {
		MemberDTO dto = new MemberDTO();
		//쿼리문 작성
		String query = "SELECT * FROM member WHERE id = ?";
		
		try {
			//쿼리 실행
			psmt = con.prepareStatement(query); //동적 쿼리문 준비
			psmt.setInt(1, id); //쿼리문의 첫 번째 인파라미터에 값 설정
//			psmt.setInt(1, dto.getId()); //쿼리문의 첫 번째 인파라미터에 값 설정
			rs = psmt.executeQuery(); //쿼리문 실행
			
			//결과처리
			if(rs.next()) {
				dto.setId(rs.getInt("id"));
//				dto.setId(rs.getInt(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getDate(4));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
	
//	입력
	public MemberDTO addMember(MemberDTO dto) {
		try {
			String query = "INSERT INTO member(pass, name) VALUES (?,?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPass());
			psmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	//수정
	public int updateMember(MemberDTO dto) {
		int result = 0;
		try {
			
			String query = "UPDATE member SET pass = ?, name = ? WHERE id = ? ";
			
			psmt = con.prepareStatement(query);
			
			// 각 쿼리의 파라미터에 dto에서 값을 설정
			psmt.setString(1, dto.getPass()); //쿼리문의 첫 번째 인파라미터에 값 설정
			psmt.setString(2, dto.getName());
			psmt.setInt(3, dto.getId());
			
			// 쿼리 실행 및 업데이트된 행 수 반환
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("UPDATE 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	//삭제
	public int removeMember(MemberDTO dto) {
		int result = 0;
		try {
			String query = "DELETE FROM member WHERE id = ? ";
			
			psmt = con.prepareStatement(query);
			
			psmt.setInt(1, dto.getId());
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("DELET 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
//	//삭제
//	public int removeMember(Integer id) {
//		MemberDTO dto = new MemberDTO();
//		int result = 0;
//		try {
//			String query = "DELETE FROM member WHERE id = ? ";
//			
//			psmt = con.prepareStatement(query);
//			
//			psmt.setInt(1, id);
//			result = psmt.executeUpdate();
//			
//		} catch (Exception e) {
//			System.out.println("DELET 중 예외 발생");
//			e.printStackTrace();
//		}
//		return result;
//	}
}


















