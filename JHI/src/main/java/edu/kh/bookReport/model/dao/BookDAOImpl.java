package edu.kh.bookReport.model.dao;

import static edu.kh.bookReport.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.bookReport.model.dto.BookReport;

public class BookDAOImpl implements BookDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BookDAOImpl() {
		try {
			prop = new Properties();
			String path = BookDAOImpl.class.getResource("/edu/kh/bookReport/sql/sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(path));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 독서 기록 목록 조회
	@Override
	public List<BookReport> selectAll(Connection conn) throws SQLException {

		List<BookReport> bookReport = new ArrayList<BookReport>();
		
		try {
			String sql = prop.getProperty("selectAll");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int bookNo = rs.getInt("BOOK_NO");
				String bookTitle = rs.getString("BOOK_TITLE");
				String bookAuthor = rs.getString("BOOK_AUTHOR");
				String regDate = rs.getString("REG_DATE");
				
				BookReport book = new BookReport(bookNo, bookTitle, bookAuthor, regDate);
				bookReport.add(book);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return bookReport;
	}

	// 독서록 상세 조회
	@Override
	public BookReport selectBook(Connection conn, int bookNo) throws SQLException {
		BookReport book = null;
		
		try {
			String sql = prop.getProperty("selectBook");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				book = new BookReport();
				
				book.setBookNo(rs.getInt("BOOK_NO"));
				book.setBookTitle(rs.getString("BOOK_TITLE"));
				book.setBookContent(rs.getString("BOOK_CONTENT"));
				book.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				book.setRegDate(rs.getString("REG_DATE"));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return book;
	}

	// 독서기록 추가
	@Override
	public int addBook(Connection conn, String bookTitle, String bookContent, String bookAuthor) throws SQLException {
		int result = 0;
		
		try {
			String sql = prop.getProperty("addBook");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bookTitle);
			pstmt.setString(2, bookContent);
			pstmt.setString(3, bookAuthor);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	// 삭제하기
	@Override
	public int deleteBook(Connection conn, int bookNo) throws SQLException {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBook");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  bookNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 수정하기
	@Override
	public int updateBook(Connection conn, BookReport book) throws SQLException {

		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBook");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookTitle());
			pstmt.setString(2, book.getBookAuthor());
			pstmt.setString(3, book.getBookContent());
			pstmt.setInt(4, book.getBookNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	

}
