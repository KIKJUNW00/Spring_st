package edu.pnu.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBCconnect {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	
	// 세번째 생성자
		public JDBCconnect(ServletContext application) {
			try {
				// JDBC 드라이버 로드
				String driver = application.getInitParameter("MySQLDriver");

				// DB에 연결
				String url = application.getInitParameter("MySQLURL");
				String id = application.getInitParameter("MySQLId");
				String pwd = application.getInitParameter("MySQLPwd");
				con = DriverManager.getConnection(url, id, pwd);
				
				System.out.println("DB 연결 성공 (인수 생성자 2)");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		// 두번째 생성자ㅣ
		public JDBCconnect(String driver, String url, String id, String pwd) {
			try {
				Class.forName(driver);

				con = DriverManager.getConnection(url, id, pwd);

				System.out.println("DB 연결 성공(인수 생성자 1)");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		public JDBCconnect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				String url = "jdbc:mysql://localhost:3306/bootmission";
				String id = "root";
				String pwd = "1234";
				con = DriverManager.getConnection(url, id, pwd);

				System.out.println("DB 연결 성공 (기본생성자)");
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
		public void close() {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();

				System.out.println("JDBC 자원 해제");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
