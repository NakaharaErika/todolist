package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CheckFalseCount;
import service.DBWorkService;
import service.ResetAccount;

@WebServlet("/reset")
public class ResetAccountServlet extends HttpServlet {
	
	ResetAccount reset = new ResetAccount();
	CheckFalseCount check = new CheckFalseCount();
	DBWorkService service = new DBWorkService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // ユーザーから送信されたユーザーIDとパスワードを取得する。
	    String userId = request.getParameter("user_id");
	    String password = request.getParameter("password");
	    String view = null;
	    //ユーザーIDが存在するかチェック
	    if(service.checkUserIDExist(userId)) {
	    	if( password != null) {
			    //パスワードをリセットする
		    	reset.ResetPass(userId,password);
		    	request.setAttribute("message", "新しいパスワードをセットしました");
		    	view = "/WEB-INF/views/login.jsp";
	    	} else {
	    		request.setAttribute("message", "パスワードを入力してください");
		    	view = "/WEB-INF/views/reset.jsp";
	    	}
	    } else {
	    	//IDが存在しない場合
	    	String falseMessage = "IDが存在しません";
	    	request.setAttribute("message", falseMessage);
	        view = "/WEB-INF/views/reset.jsp";
	    }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
}
