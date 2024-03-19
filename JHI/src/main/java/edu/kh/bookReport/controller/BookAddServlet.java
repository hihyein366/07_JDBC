package edu.kh.bookReport.controller;

import java.io.IOException;

import edu.kh.bookReport.model.service.BookReportService;
import edu.kh.bookReport.model.service.BookReportServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/book/add")
public class BookAddServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/add.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BookReportService service = new BookReportServiceImpl();
			
			
			String bookTitle = req.getParameter("bookTitle");
			String bookContent = req.getParameter("bookContent");
			String bookAuthor = req.getParameter("bookAuthor");
			
			int result = service.addBook(bookTitle, bookContent, bookAuthor);
			
			String message = null;
			
			if(result > 0) message = "오늘도 한 권을 기록했어요";
			else message = "기록 추가에 실패했습니다.";
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			resp.sendRedirect("/");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}


}
