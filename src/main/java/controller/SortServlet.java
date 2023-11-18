package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import entity.DBWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.DBWorkService;

@WebServlet("/sort")
public class SortServlet extends HttpServlet {
	
	private DBWorkService service = new DBWorkService(); 
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
		
		if (loggedInUser != null) {
			String item = request.getParameter("items");
			String sort = request.getParameter("sort");
			
			
	        List<HashMap<String, String>> todos = service.getTodoListBySort(loggedInUser.getNo(), item, sort);
	        request.setAttribute("rows", todos);
	        request.setAttribute("message", sortItem(item) + " 順に " + sortStr(sort) + " で並べ替えました");
			String view = "/WEB-INF/views/list.jsp";
	        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		} else {
	    	response.sendRedirect("start");
	    }
	}
	
	
	protected String sortItem(String item) {
		switch(item) {
		case "id":
			return "ID";
		case "title":
			return "タイトル";
		case "genre":
			return "ジャンル";
		case "date":
			return "登録日";
		case "priority":
			return "優先度";
		default:
			return "";
		}
	}
	
	protected String sortStr(String sort) {
		return sort.equals("asc")? "昇順" : "降順";
	}
}