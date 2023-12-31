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
import service.DBWorkService;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	
	private DBWorkService service = new DBWorkService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
		
			if (request.getAttribute("message") == null) {
				request.setAttribute("message", "todoを管理しましょ");
			}
		
		String postId = request.getParameter("id"); 
		String title = request.getParameter("title"); 
		String content = request.getParameter("content"); 
		String genre = request.getParameter("genre"); 
		String priority = request.getParameter("priority"); 
	
		
            service.updateTodoList(postId,title,content,genre,priority);
            request.setAttribute("message", "ID:" + postId + "の更新ができました");
       
        String forward = "show?id=" + postId;
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
		} else {
	    	response.sendRedirect("start");
	    }
	}

}