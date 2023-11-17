package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DBWorkService;

@WebServlet("/new")
public class NewServlet extends HttpServlet {
	
	private DBWorkService service = new DBWorkService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setAttribute("message", "新規作成ページです");
			
			HashMap<String, String> todoDetails = service.getTodoListByNo(postId); // 型変換は不要
            request.setAttribute("todoDetails", todoDetails);
			List<HashMap<String, String>> priorities = service.getListPriorities();
            request.setAttribute("priorities", priorities);
            
			String view = "/WEB-INF/views/new.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			
			
		}


}