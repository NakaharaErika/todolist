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

@WebServlet("/start")
public class StartServlet extends HttpServlet {
	
	DBWorkService service = new DBWorkService();
	CheckFalseCount check = new CheckFalseCount();
	
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
	
	    DBWork dbWork = service.login(userId, password);//DBWorkクラスにuserId,name,No(主キー）をセット
	    String view;
	    if (dbWork != null) {
	        // ログイン成功
	    	//セッション情報を保持
	    	HttpSession session = request.getSession();//パスワード以外の情報をセッションとして保持
	        session.setAttribute("loggedInUser", dbWork);
	    	
	        view = "/list";
	    } else {
	        // ログイン失敗
	    	//accountテーブルの失敗カウントを調べる
	    	String falseMessage = (check.createAccount())? "IDかパスワードが異なります":"３回間違えたのでロックしました"
	    	
	        request.setAttribute("message", falseMessage);
	        view = "/WEB-INF/views/login.jsp";
	    }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
}
