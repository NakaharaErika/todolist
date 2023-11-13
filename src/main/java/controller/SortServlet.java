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
		String item = request.getParameter("items");
		String sort = request.getParameter("sort");
		
		HttpSession session = request.getSession();
		DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
		
		if (loggedInUser != null) {
	        List<HashMap<String, String>> todos = service.getTodoListBySort(loggedInUser.getNo(), item, sort);
	        request.setAttribute("rows", todos);
	        request.setAttribute("message", item + "順に並べ替えました");
	    } else {
	        // セッションにユーザー情報がない場合の処理
	        request.setAttribute("errorMessage", "セッションがタイムアウトしました。もう一度ログインしてください。");
	    }
		String view = "/WEB-INF/views/list.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}