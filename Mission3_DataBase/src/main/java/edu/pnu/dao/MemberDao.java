package edu.pnu.dao;

import java.util.List;
import java.util.Vector;

import edu.pnu.connection.JDBCconnect;
import edu.pnu.domain.MemberDTO;

public class MemberDao extends JDBCconnect{
	
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
	
}


















