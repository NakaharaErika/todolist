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
import service.GetTodoListByUserId;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	
	GetTodoListByUserId list = new GetTodoListByUserId();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
	     DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
	 if (loggedInUser != null) {
	    	//ユーザーのtodoリストを格納
	    	List<HashMap<String, String>> todos;
			try {
				todos = list.getTodoListByUserId(loggedInUser.getNo());
				request.setAttribute("rows", todos);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}      
	 } else {
		response.sendRedirect("login");
    }
	 	
	    String view = "/WEB-INF/views/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
	     DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
	 if (loggedInUser != null) {
	    	//ユーザーのtodoリストを格納
	    	List<HashMap<String, String>> todos;
			try {
				todos = list.getTodoListByUserId(loggedInUser.getNo());
				request.setAttribute("rows", todos);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}      
	 } else {
		response.sendRedirect("login");
   }
	 	
	    String view = "/WEB-INF/views/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
	
}
