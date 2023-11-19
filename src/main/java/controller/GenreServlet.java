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

@WebServlet("/editGenre")
public class GenreServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
        	if (request.getAttribute("message") == null) {
    			request.setAttribute("message", "todoを管理しましょ");
    		}
        	//ここでloggedInUserがtodoリスト内で保持しているジャンル一覧を取得したい
        	
            String view = "/WEB-INF/views/genre.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
        } else {
        	response.sendRedirect("start");
        }

        
	}


}