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

/**
 * Servlet implementation class GetSubFoldersFiles
 */
public class GetSubFoldersFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetSubFoldersFiles() {
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
		String fid = request.getParameter("id");
		Service service = new ServiceImpl();
		System.out.println("received fid");
		Folder folder = service.getFolder(Integer.parseInt(fid));
		HttpSession session = request.getSession(false);
		session.setAttribute("curFolder", folder);
		request.setAttribute("folders", service.getFolders(folder));
		request.setAttribute("files", service.getFiles(folder));
		request.getRequestDispatcher("home.jsp").forward(request, response);
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
