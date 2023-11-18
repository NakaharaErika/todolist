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

@WebServlet("/createGenre")
public class CreateGenreServlet extends HttpServlet {
	
	private DBWorkService service = new DBWorkService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
		
			if (request.getAttribute("message") == null) {
				request.setAttribute("message", "todoを管理しましょ");
			}
		
		String genre1 = request.getParameter("genre1"); 
		String genre2 = request.getParameter("genre2"); 
		String genre3 = request.getParameter("genre3"); 
		
            service.editGenreList(loggedInUser,genre1,genre2,genre3);
            request.setAttribute("message", "ジャンル編成を更新しました");
       
            String view = "/WEB-INF/views/edit.jsp";
	        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	        dispatcher.forward(request, response);
		
        } else {
	    	response.sendRedirect("start");
	    }
	}

}