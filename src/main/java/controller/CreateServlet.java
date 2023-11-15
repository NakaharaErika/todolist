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

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	
	private DBWorkService service = new DBWorkService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "todoを管理しましょ");
		}
		
		String title = request.getParameter("title"); 
		String content = request.getParameter("content"); 
		String genre = request.getParameter("genre"); 
		String priority = request.getParameter("priority"); 
		String date = request.getParameter("date"); 
		
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            service.createTodoList(title,content,genre,priority,date);
            request.setAttribute("message", "タイトル:" + title + "の新規作成ができました");
        } else {
            request.setAttribute("errorMessage", "セッションがタイムアウトしました。もう一度ログインしてください。");
        }

        String forward = "/list";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
	}


}