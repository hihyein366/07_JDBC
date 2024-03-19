package edu.kh.bookReport.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.kh.bookReport.model.dto.BookReport;
import edu.kh.bookReport.model.service.BookReportService;
import edu.kh.bookReport.model.service.BookReportServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("")
public class MainServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			BookReportService service = new BookReportServiceImpl();
			
			Map<String, Object> map = service.selectAll();
			
			List<BookReport> bookReport = (List<BookReport>)map.get("bookReport");
			
			req.setAttribute("bookReport", bookReport);
			
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
