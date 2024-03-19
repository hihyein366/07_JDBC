package edu.kh.bookReport.controller;

import java.io.IOException;

import edu.kh.bookReport.model.service.BookReportService;
import edu.kh.bookReport.model.service.BookReportServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/book/delete")
public class BookDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		
			BookReportService service = new BookReportServiceImpl();
			
			int bookNo = Integer.parseInt(req.getParameter("bookNo"));
			
			int result = service.deleteBook(bookNo);
			
			String path = null;
			String message = null;
			
			if(result > 0) {
				path = "/";
				message = "기록이 삭제되었습니다.";
			} else {
				path = "/book/detail?bookNo=" + bookNo;
				message = "기록이 삭제되지 않았습니다";
			}
			
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	
	
	}

	
}
