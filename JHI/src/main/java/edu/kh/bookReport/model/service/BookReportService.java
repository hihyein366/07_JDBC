package edu.kh.bookReport.model.service;

import java.sql.SQLException;
import java.util.Map;

import edu.kh.bookReport.model.dto.BookReport;

public interface BookReportService {

	/** 독서록 목록
	 * @return map
	 * @throws SQLException
	 */
	Map<String, Object> selectAll() throws SQLException;

	/** 독서록 상세 조회
	 * @param bookNo
	 * @return book
	 * @throws SQLException
	 */
	BookReport selectBook(int bookNo) throws SQLException;

	/** 독서록 추가하기
	 * @param bookTitle
	 * @param bookContent
	 * @return result
	 * @throws SQLException
	 */
	int addBook(String bookTitle, String bookContent, String bookAuthor) throws SQLException;

	/** 기록 삭제
	 * @param bookNo
	 * @return result
	 * @throws SQLException
	 */
	int deleteBook(int bookNo) throws SQLException;

	/** 기록 수정
	 * @param book
	 * @return result
	 * @throws SQLException
	 */
	int updateBook(BookReport book) throws SQLException;

}
