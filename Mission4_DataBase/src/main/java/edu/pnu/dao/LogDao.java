package edu.pnu.dao;

import edu.pnu.connection.JDBCconnect;
import edu.pnu.domain.LogDTO;

public class LogDao extends JDBCconnect{
	 // Log 데이터를 삽입하는 메서드
    public int insertLog(LogDTO log) {
        String query = "INSERT INTO dblog (method, sqlstring, success) VALUES (?, ?, ?)";
        int result = 0;

        try {
            
        	psmt = con.prepareStatement(query); 
            // PreparedStatement에 파라미터 값 설정
            psmt.setString(1, log.getMethod());
            psmt.setString(2, log.getSqlstring());
            psmt.setBoolean(3, log.isSuccess());
            
            // 쿼리 실행 및 결과 반환
            result = psmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result; // 삽입된 행의 개수 반환
    }
}
