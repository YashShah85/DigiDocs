package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.Service;
import com.service.impl.ServiceImpl;
import com.shared.Folder;
import com.shared.User;

/**
 * Servlet implementation class CreateFolder
 */
public class CreateFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateFolder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Creating Folder");
		HttpSession session= request.getSession(false);
		Service service = new ServiceImpl();
		Folder folder = new Folder();
		folder.setFolderName(request.getParameter("name"));
		folder.setUser((User)session.getAttribute("user"));
		if(((Folder)session.getAttribute("curFolder")) != null)
			folder.setFolder((Folder)session.getAttribute("curFolder"));
		service.addFolder(folder);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
