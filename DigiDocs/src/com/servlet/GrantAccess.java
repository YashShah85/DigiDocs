package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.Service;
import com.service.impl.ServiceImpl;
import com.shared.File;
import com.shared.User;

/**
 * Servlet implementation class GrantAccess
 */
public class GrantAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GrantAccess() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Service service = new ServiceImpl();
		HttpSession session = request.getSession(false);
		System.out.println("grant access called");
		File file = service.getFile(Integer.parseInt(request.getParameter("fileId")));
		if (file.getUser().getEmail().equals(((User) session.getAttribute("user")).getEmail())) {
			System.out.println("owner validated");
			User user = service.getUserForAccess(request.getParameter("userId"));
			if (user != null) {
				System.out.println("user recived");
				List<File> files = new ArrayList<>();
				files.add(file);
				user.setFiles2(files);
				service.addUser(user);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
