package edu.kh.bookReport.controller;

import java.io.IOException;

import edu.kh.bookReport.model.dto.BookReport;
import edu.kh.bookReport.model.service.BookReportService;
import edu.kh.bookReport.model.service.BookReportServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/book/update")
public class BookUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			BookReportService service = new BookReportServiceImpl();
			
			int bookNo = Integer.parseInt(req.getParameter("bookNo"));
			
			BookReport book = service.selectBook(bookNo);
			
			if(book != null) {
				req.setAttribute("book", book);
				String path = "/WEB-INF/views/update.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			} else {
				req.getSession().setAttribute("message", "존재하지 않는 기록입니다");
				
				resp.sendRedirect("/");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BookReportService service = new BookReportServiceImpl();
			
			int bookNo = Integer.parseInt(req.getParameter("bookNo"));
			String bookTitle = req.getParameter("bookTitle");
			String bookAuthor = req.getParameter("bookAuthor");
			String bookContent = req.getParameter("bookContent");
			
//			BookReport book = new BookReport(bookNo, bookTitle, bookAuthor, bookContent);
			BookReport book = new BookReport();
			book.setBookNo(bookNo);
			book.setBookTitle(bookTitle);
			book.setBookAuthor(bookAuthor);
			book.setBookContent(bookContent);
			
					
			int result = service.updateBook(book);
			
			String path = null;
			String message = null;
			
			if(result > 0) {
				path = "/book/detail?bookNo=" + bookNo;
				message = "수정이 완료되었습니다.";
			} else {
				path = "/book/update?bookNo=" + bookNo;
				message = "수정에 실패하였습니다.";
			}
			
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
