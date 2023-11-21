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
import service.GetListPriorities;

@WebServlet("/new")
public class NewServlet extends HttpServlet {
	
	private GetListPriorities service = new GetListPriorities();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
			request.setAttribute("message", "新規作成ページです");
			
			List<HashMap<String, String>> priorities;
			try {
				priorities = service.getListPriorities();
				request.setAttribute("priorities", priorities);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
            
			String view = "/WEB-INF/views/new.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
        } else {
        	response.sendRedirect("start");	
		}
	}

}