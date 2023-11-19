package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CreateAccount;

@WebServlet("/account")
public class CreateAccountServlet extends HttpServlet {
    
    CreateAccount createaccount = new CreateAccount();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // ユーザーから送信された情報を取得
        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");
        String userName = request.getParameter("name");
        
        String message;
        String view;
        
        // アカウント作成の試み
        Boolean accountCreated =  createaccount.createAccount(userId, password, userName);
        if(accountCreated) {
            view = "/WEB-INF/views/login.jsp";
            message = "アカウントを新規作成しました";
        } else {
            view = "/WEB-INF/views/account.jsp";
            message = "このアカウント名はすでに存在しています";
        }
        
        request.setAttribute("message", message);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
