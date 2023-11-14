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

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	
	private DBWorkService service = new DBWorkService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "todoを管理しましょ");
		}
		
		String postId = request.getParameter("id"); // String 型のまま使用
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
        	service.destroyTodoList(postId);
            request.setAttribute("message", "ID:" + postId + "の削除ができました");
        } else {
            request.setAttribute("errorMessage", "セッションがタイムアウトしました。もう一度ログインしてください。");
        }

        String view = "/WEB-INF/views/edit.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
	}


}