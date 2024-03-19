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

@WebServlet("/book/detail")
public class BookDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			BookReportService service = new BookReportServiceImpl();
			
			int bookNo = Integer.parseInt(req.getParameter("bookNo"));
			
			BookReport book = service.selectBook(bookNo);
			
			if(book != null) {
				req.setAttribute("book", book);
				String path = "/WEB-INF/views/detail.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
			} else {
				req.getSession().setAttribute("message", "남겨진 독서 기록이 없습니다.");
				resp.sendRedirect("/");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
	
	}
	
}
