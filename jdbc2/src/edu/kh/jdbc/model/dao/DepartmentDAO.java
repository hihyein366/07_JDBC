package edu.kh.jdbc.model.dao;

// JDBCTemplate 클래스 내부의 static이 붙은 메서드를 모두 가져옴
// -> 가져온 메서드는 메서드 호출 시 클래스명을 작성하지 않아도 된다!!
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.model.dto.Department;

// DAO(Data Aceess Object) : 데이터(DB,파일) 접근하는 객체 
// -> SQL 수행, 결과 반환
public class DepartmentDAO {

	/* JDBC 객체 참조 변수 선언 */
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	
	
	/** 부서 추가(삽입) 메서드
	 * @param dept(부서코드, 부서명, 지역코드)
	 * @return result(삽입된 결과 행의 개수)
	 * @throws SQLException 
	 */
	public int insertDepartment(Department dept) throws SQLException {
		
		int result = 0; // 결과 저장용 변수
		
		try {
			// 1. Connection 얻어오기 (한 줄로 커넥션가능^^)
			conn = getConnection();
			
			// 2. SQL 작성
			String sql = "INSERT INTO DEPARTMENT4 VALUES(?, ?, ?)";
			
			// 3. PreaparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ? 에 알맞은 값 세팅
			pstmt.setString(1, dept.getDeptId());
			pstmt.setString(2, dept.getDeptTitle());
			pstmt.setString(3, dept.getLocationId());
			
			// 5. SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate(); // DML 수행
			
			// 6. 트랜잭션 제어 처리 (간단히 가져오기 가능)
			if(result > 0) 	commit(conn);
			else						rollback(conn);
		
		} finally {
			
			// 7. 사용한 JDBC 객체 자원 반환
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
	
	
	
	/** 부서 전체 조회
	 * @return deptList (전체 부서)
	 * @throws SQLException 
	 */
	public List<Department> selectAll() throws SQLException{
		
		// 결과 저장용 변수 선언
		List<Department> deptList = null;
		
		try {
			// 1. Connection 얻어오기
			conn = getConnection();
			
			// 2. SQL 작성
			String sql = "SELECT * FROM DEPARTMENT4";
			
			// 3. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 가 없으므로 값 세팅 패스~
			
			// 4. SQL 실행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			// executeQuery()  : SELECT 수행 - ResultSet 반환
			// executeUpdate() : DML 수행    - 결과 행의 수 반환
			
			
			// 5. 결과를 저장할 List를 생성 한 후
			// 한 행 씩 접근에 컬럼 값을 얻어와 List에 추가
			deptList = new ArrayList<Department>();
			
			while(rs.next()) {
				String deptId     = rs.getString("DEPT_ID");
				String deptTitle  = rs.getString("DEPT_TITLE");
				String locationId = rs.getString("LOCATION_ID");
				
				// Department 객체 생성
				Department dept = new Department(deptId, deptTitle, locationId);
				
				// deptList에 추가
				deptList.add(dept);
			}
			
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return deptList;
		
	}



	/** 부서 검색
	 * @param title
	 * @return deptList
	 * @throws SQLException
	 */
	public List<Department> selectDepartmentTitle(String title) throws SQLException{
		
		// 결과 저장용 변수 선언 또는 객체 생성
		List<Department> deptList = new ArrayList<Department>();
		
		try {
			// 1. 커넥션 얻어오기
			conn = getConnection();
			
			// 2. SQL 작성
			String sql 
				= "SELECT * FROM DEPARTMENT4       "
				+ "WHERE DEPT_TITLE LIKE '%' || ? || '%'";
			
			
			// 3. PreparedStatement 객체 생성 + SQL 적재
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?에 알맞은 값 대입
			pstmt.setString(1, title);
			
			// 5. SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
			rs = pstmt.executeQuery();
			
			// 6. 한 행씩 접근하며 컬럼 값 얻어오기
			// -> 얻어온 컬럼 값을 이용해 Department 객체를 생성한 후
			//    deptList에 추가하기
			while(rs.next()) {
				String deptId     = rs.getString("DEPT_ID");
				String deptTitle  = rs.getString("DEPT_TITLE");
				String locationId = rs.getString("LOCATION_ID");
				
				Department dept = new Department(deptId, deptTitle, locationId);
					
				deptList.add(dept);
			}
			
		}finally {
			// 7. 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return deptList;
	}
	
	
	
	/** 부서 삭제하기
	 * @param deptId
	 * @return result
	 * @throws SQLException
	 */
	public List<Department> deleteDepartement(String deptId) throws SQLException {
		
		int result = 0;
		
		try {
			conn = getConnection();
			String sql
				= "DELETE FROM DEPARTMENT4 WHERE DEPT_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptId);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) commit(conn);
			else rollback(conn);
			
		}finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}
	
	
	/**
	 * @param deptId
	 * @param deptTitle
	 * @return
	 * @throws SQLException
	 */
	public int updateDepartment(String deptId, String deptTitle) throws SQLException {
		int result = 0; // 결과 저장용 변수
		
		try {
			conn = getConnection(); // 커넥션 얻어오기
			
			String sql
				= "UPDATE DEPARTMENT4 SET DEPT_TITLE = ? "
				+ "WHERE DEPT_ID = ?";
			
			pstmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성
			
			pstmt.setString(1, deptTitle);
			pstmt.setString(2, deptId);
			
			result = pstmt.executeUpdate(); // SQL 수행 후 결과 반환 받기
			
			// 트랜잭션 제어 처리
			if(result > 0) commit(conn);
			else rollback(conn);
			
		} finally {
			
			close(pstmt);
			close(conn);
		}
		return result; // 결과 반환
	}
	

	

}
