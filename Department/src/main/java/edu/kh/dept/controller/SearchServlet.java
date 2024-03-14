package edu.kh.dept.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/department/search")
public class SearchServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			DepartmentService service = new DepartmentServiceImpl();
			
			String keyword = req.getParameter("keyword"); // servlet에 적은 naem 속성으로 가져옴
			
			List<Department> result = service.searchDepartment(keyword);
			
			
			HttpSession session = req.getSession();
			
			if(result.isEmpty()) {
				req.getSession().setAttribute("message", "해당 부서가 존재하지 않다");
				resp.sendRedirect("/department/selectAll");
				
			} else {
				req.setAttribute("result", result);
				// froward할 JSP 경로
				String path = "/WEB-INF/views/search.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
