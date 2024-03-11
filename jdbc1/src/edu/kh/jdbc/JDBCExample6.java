package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample6 {
	public static void main(String[] args) {
		
		Connection conn = null;
		ResultSet rs = null;
		
		// PreparedStatement (준비된 Statement)
		// - 외부 변수 값을 SQL에 받아드릴 준비가 되어있는 Statement
		// - 성능, 속도면에서 우위를 가지고 있음
		// - ? (placeholder) : 변수/값을 위치 시킬 자리 지정
		PreparedStatement pstmt = null;
		
		 
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@"; // 드라이버 종류
			String host = "localhost"; // DB 서버 컴퓨터의 IP 주소
			String port = ":1521"; // DB 서버 컴퓨터에 DB 프로그램 연결 번호
			String dbName = ":xe"; // DB 이름
			String userName = "KH_JHI"; // 사용자 계정
			String pw = "KH1234"; // 계정 비밀번호
			
			conn = DriverManager.getConnection(type + host + port + dbName, userName, pw);
			
			/* 만들어진 커넥션으로 SQL 수행 시 자동 커밋 비활성화 */
			conn.setAutoCommit(false);
			
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("부서코드 입력 : ");
			String deptCode = sc.next();
			
			System.out.print("부서명 입력 : ");
			String deptTitle = sc.next();
			
			System.out.print("지역코드 입력 : ");
			String locationId = sc.next();
			
			String sql = "INSERT INTO DEPARTMENT4 VALUES (?, ?, ?)";
			
			/* 4. PreparedStatement 객체 생성 */
			/* sql을 객체 생성 시 전달하여 ?에 값을 대입할 준비를 함 */
			pstmt = conn.prepareStatement(sql);
			
			
			/* 5. PreparedStatement 객체의 ? 부분에 알맞은 값 대입 */
			// pstmt.set자료형(?순서, 값) : ?에 값 세팅
			// --> 해당 구문으로 세팅되는 값은 자동으로 양쪽에 '' 추가됨
			pstmt.setString(1, deptCode);
			pstmt.setString(2, deptTitle);
			pstmt.setString(3, locationId);
			
			/* 6. SQL 수행 후 결과 반환 받기 */
			/* DML 수행 결과는 결과 행의 수 */
			// INSERT, UPDATE, DELETE 묶어서 UPDATE로 취급
			int result = pstmt.executeUpdate();
			// -> PreparedStatement SQL 수행 시 메서드 매개변수로 SQL 전달하면 안된다!!
			
			
			/* 7. 수행 결과에 따라 트랜잭션 제어 처리 */
			if(result > 0) {
				System.out.println("삽입 성공");
				conn.commit();
			} else {
				System.out.println("삽입 실패");
				conn.rollback();
			}
			
			System.out.println(rs);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			/* 8. JDBC 자원 반환 */
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
