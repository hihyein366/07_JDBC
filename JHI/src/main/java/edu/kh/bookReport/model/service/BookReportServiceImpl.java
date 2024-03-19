package edu.kh.bookReport.model.service;

import static edu.kh.bookReport.common.JDBCTemplate.*;

import static edu.kh.bookReport.common.JDBCTemplate.close;
import static edu.kh.bookReport.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.bookReport.model.dao.BookDAO;
import edu.kh.bookReport.model.dao.BookDAOImpl;
import edu.kh.bookReport.model.dto.BookReport;

public class BookReportServiceImpl implements BookReportService {
	
	private BookDAO dao = null;
	
	public BookReportServiceImpl() {
		dao = new BookDAOImpl();
	}

	// 독서록 목록
	@Override
	public Map<String, Object> selectAll() throws SQLException {
		
		Connection conn = getConnection();
		
		List<BookReport> bookReport = dao.selectAll(conn);
		
		close(conn);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("bookReport", bookReport);

		return map;
	}

	// 독서록 상세조회
	@Override
	public BookReport selectBook(int bookNo) throws SQLException {
		
		Connection conn = getConnection();
		
		BookReport book = dao.selectBook(conn, bookNo);
		
		close(conn);
		
		return book;
	}

	// 독서록 추가하기
	@Override
	public int addBook(String bookTitle, String bookContent, String bookAuthor) throws SQLException {

		Connection conn = getConnection();
		
		int result = dao.addBook(conn, bookTitle, bookContent, bookAuthor);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	@Override
	public int deleteBook(int bookNo) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.deleteBook(conn, bookNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);

		return result;
	}
	
	// 수정하기
	@Override
	public int updateBook(BookReport book) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.updateBook(conn, book);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

}
