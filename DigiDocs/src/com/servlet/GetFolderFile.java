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
 * Servlet implementation class GetFolderFile
 */
public class GetFolderFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFolderFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Service service = new ServiceImpl();
		HttpSession session = request.getSession(false);
		if((Folder)session.getAttribute("curFolder") != null)
		{
			request.setAttribute("folders", service.getFolders((Folder)session.getAttribute("curFolder")));
			request.setAttribute("files", service.getFiles((Folder)session.getAttribute("curFolder")));
		}
		else
			request.setAttribute("folders", service.getRootFolders());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
