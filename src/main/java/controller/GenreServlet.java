package controller;

import java.io.IOException;
import java.util.List;

import entity.DBWork;
import entity.GenreWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.GenreWorkService;

@WebServlet("/editGenre")
public class GenreServlet extends HttpServlet {
	
	GenreWorkService service = new GenreWorkService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
        	if (request.getAttribute("message") == null) {
    			request.setAttribute("message", "todoを管理しましょ");
    		}
        	//ここでloggedInUserがtodoリスト内で保持しているジャンル一覧を取得したい
        	List<GenreWork> userGenres = genreService.getGenresForUser(loggedInUser.getUserId());
        	request.setAttribute("userGenres",usergenres);
        	
            String view = "/WEB-INF/views/genre.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
        } else {
        	response.sendRedirect("start");
        }
	}
}