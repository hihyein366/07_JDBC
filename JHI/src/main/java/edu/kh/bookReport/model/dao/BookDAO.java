package edu.kh.bookReport.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.bookReport.model.dto.BookReport;

public interface BookDAO {

	/** 독서록 목록 조회
	 * @param conn
	 * @return bookReport
	 * @throws SQLException
	 */
	List<BookReport> selectAll(Connection conn) throws SQLException;

	/** 독서록 상세 조회
	 * @param conn
	 * @param bookNo
	 * @return book
	 * @throws SQLException
	 */
	BookReport selectBook(Connection conn, int bookNo) throws SQLException;

	/** 독서록 추가
	 * @param conn
	 * @param bookTitle
	 * @param bookContent
	 * @return result
	 * @throws SQLException
	 */
	int addBook(Connection conn, String bookTitle, String bookContent, String bookAuthor) throws SQLException;

	/** 기록 삭제
	 * @param conn
	 * @param bookNo
	 * @return result
	 * @throws SQLException
	 */
	int deleteBook(Connection conn, int bookNo) throws SQLException;

	/** 수정하기
	 * @param conn
	 * @param book
	 * @return result
	 * @throws SQLException
	 */
	int updateBook(Connection conn, BookReport book) throws SQLException;


}
