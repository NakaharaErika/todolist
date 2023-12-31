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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	
	private DBWorkService service = new DBWorkService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
        	if (request.getAttribute("message") == null) {
    			request.setAttribute("message", "todoを管理しましょ");
    		}
        	
        	String postId = request.getParameter("id");
        	//todoのID（主キー）に紐づくtodoの詳細を取得
            HashMap<String, String> todoDetails = service.getTodoListByNo(postId);
            request.setAttribute("todoDetails", todoDetails);
            
            List<HashMap<String, String>> priorities = service.getListPriorities();
            request.setAttribute("priorities", priorities);
            
            String view = "/WEB-INF/views/edit.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
        } else {
        	response.sendRedirect("start");
        }

        
	}


}