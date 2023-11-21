package controller;

import java.io.IOException;
import java.util.HashMap;

import entity.DBWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.GetTodoListByNo;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {

    private GetTodoListByNo service = new GetTodoListByNo();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	 HttpSession session = request.getSession();
         DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

         if (loggedInUser != null) {
    	
		    	if (request.getAttribute("message") == null) {
		            request.setAttribute("message", "todoを管理しましょう");
		        }
		
		        String postId = request.getParameter("id"); // String 型のまま使用
		        	//todoの取り出し
		            HashMap<String, String> todoDetails;
					try {
						todoDetails = service.getTodoListByNo(postId);
						request.setAttribute("todoDetails", todoDetails);
			            
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} 
		            
		        } else {
		        	response.sendRedirect("login");
		        }

        String view = "/WEB-INF/views/post.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
