package edu.pnu.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.pnu.connection.JDBCconnect;

@Repository
public class LogDao extends JDBCconnect{
	 // Log 데이터를 삽입하는 메서드
    public void insertLog(Map<String, Object> map ) {
        String method = (String) map.get("method");
		String sqlstring = (String) map.get("sqlstring");
		boolean success = (boolean) map.get("success");
		
        int result = 0;
        String query = "INSERT INTO dblog (method, sqlstring, success) VALUES (?, ?, ?)";

        try {
            
        	psmt = con.prepareStatement(query); 
            // PreparedStatement에 파라미터 값 설정
            psmt.setString(1, method);
            psmt.setString(2, sqlstring);
            psmt.setBoolean(3,success);
            
            // 쿼리 실행 및 결과 반환
            result = psmt.executeUpdate();
            
        } catch (SQLException e) {
        	System.out.println("예외발생");
            e.printStackTrace();
        }
        finally {
			if(result==1) {
				System.out.println("로그 추가 성공");
			} else 
				System.out.println("로그 추가 실패");
		}
    }
}
