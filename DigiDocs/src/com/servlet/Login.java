package com.servlet;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.Service;
import com.service.impl.ServiceImpl;
import com.shared.User;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		Service service = new ServiceImpl();
		User user = service.getUser(email, password);
		if(user != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			if(user.getProfilepic() != null)
				session.setAttribute("profilepic", Base64.getEncoder().encodeToString(user.getProfilepic()));
			else
				session.setAttribute("profilepic", null);
			response.sendRedirect("home.jsp");
		}
		else
			response.sendRedirect("login.jsp");
	}

}
