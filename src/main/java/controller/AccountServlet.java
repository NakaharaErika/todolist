package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DBWorkService;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	
	DBWorkService service = new DBWorkService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // ユーザーから送信されたユーザーIDとパスワードを取得する。
	    String userId = request.getParameter("user_id");
	    String password = request.getParameter("password");
	    String userName = request.getParameter("name");
	    String genre1 = request.getParameter("genre1");
	    String genre2 = request.getParameter("genre2");
	    String genre3 = request.getParameter("genre3");
	    
	    String message;
	    String view;
	    
	    Boolean account =  service.createAccount(userId, password,userName,genre1,genre2,genre3);
	    if(account) {
	    	view = "/WEB-INF/views/login.jsp";
	    	message ="アカウントを新規作成しました";
	    	request.setAttribute("message", message);
	    } else {
	    	view = "/WEB-INF/views/account.jsp";
	    	message ="このアカウント名はすでに存在しています";
	    	request.setAttribute("message", message);
	    }
	    
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
}
