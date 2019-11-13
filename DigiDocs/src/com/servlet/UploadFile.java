package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.service.Service;
import com.service.impl.ServiceImpl;
import com.shared.File;
import com.shared.Folder;
import com.shared.User;

/**
 * Servlet implementation class UploadFile
 */
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
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
		String access = request.getParameter("access");
		Service service = new ServiceImpl();
		Part part= request.getPart("file");
		if(part!= null)
		{
			System.out.println("successfully received file");
			System.out.println(service.getFilename(part));
			boolean flag=service.uploadFile(part);
			if(flag==true)
			{
				System.out.println("file uploaded successfully");
				File file= new File();
				HttpSession session = request.getSession(false);
				file.setFileName(service.getFilename(part));
				file.setAccess(access);
				file.setUrl("https://mywebsitestorage91.z13.web.core.windows.net/" + service.getFilename(part));
				file.setUser((User)session.getAttribute("user"));
				file.setFolder((Folder)session.getAttribute("curFolder"));
				service.addFile(file);
				request.setAttribute("files", service.getFiles((Folder)session.getAttribute("curFolder")));
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		}
	}

}
