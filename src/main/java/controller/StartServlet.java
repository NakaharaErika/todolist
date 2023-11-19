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
import service.CheckFalseCount;
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
	    String view = null;
	    //ユーザーIDが存在するかチェック
	    if(service.checkUserIDExist(userId)) {
	    	//アカウントがロックされていないかチェック
	    	if(!check.isAccountLocked(userId)){
		    	//パスワードが一致しているかチェック
		    	DBWork dbWork = service.login(userId, password);//DBWorkクラスにuserId,name,No(主キー）をセット
		    	if(dbWork != null) {//ログイン成功
		    		//パスワードが一致していたら、フラグを０に戻してdbWrokに値を設定
			    	check.countReset(userId);
			    	HttpSession session = request.getSession();//パスワード以外の情報をセッションとして保持
			        session.setAttribute("loggedInUser", dbWork);
			        
			        view = "/list";
		    	} else {//ログイン失敗
		    		//一致していなかったら失敗カウントを調べる
		    		boolean canRetry = check.incrementFalseCnt(userId);
		    		String falseMessage = canRetry? "IDかパスワードが異なります":"３回間違えたのでロックしました";
		    		request.setAttribute("message", falseMessage);
		    		view = "/WEB-INF/views/login.jsp";
		    	}
	    	} else {
	    		//ロックされていたらエラーメッセージを表示
	    		request.setAttribute("message", "このアカウントはロックされています");
	    		view = "/WEB-INF/views/login.jsp";
	    	}
	    } else {
	    	//IDが存在しない場合
	    	String falseMessage = "IDが存在しません";
	    	request.setAttribute("message", falseMessage);
	        view = "/WEB-INF/views/login.jsp";
	    }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
}
