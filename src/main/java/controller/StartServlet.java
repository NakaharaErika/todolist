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

@WebServlet("/start")
public class StartServlet extends HttpServlet {
	
	DBWorkService service = new DBWorkService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン画面を表示
		String view = "/WEB-INF/views/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // ユーザーから送信されたユーザーIDとパスワードを取得する。
	    String userId = request.getParameter("user_id");
	    String password = request.getParameter("password");
	
	    DBWork dbWork = service.login(userId, password);
	    String view;
	    if (dbWork != null) {
	        // ログイン成功
	    	//セッション情報を保持
	    	HttpSession session = request.getSession();
	        session.setAttribute("loggedInUser", dbWork);
	    	//ユーザーのtodoリストを格納
	    	List<HashMap<String, String>> todos = service.getTodoListByUserId(dbWork.getNo());
	        request.setAttribute("rows", todos);
	        view = "/WEB-INF/views/list.jsp";
	    } else {
	        // ログイン失敗
	        request.setAttribute("loginFailure", "IDかパスワードが異なります");
	        view = "/WEB-INF/views/login.jsp";
	    }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
}
