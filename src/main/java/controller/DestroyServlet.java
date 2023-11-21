package controller;

import java.io.IOException;

import entity.DBWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.DestroyTodo;

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	
	private DestroyTodo service = new DestroyTodo();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "todoを管理しましょ");
		}
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {

    		String postId = request.getParameter("id"); // String 型のまま使用
        	try {
				service.destroyTodoList(postId);
				request.setAttribute("message", "ID:" + postId + "の削除ができました");
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
            
   
	        String view = "/WEB-INF/views/edit.jsp";
	        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	        dispatcher.forward(request, response);
		} else {
	    	response.sendRedirect("start");
	    }
	}
}