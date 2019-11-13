package com.service.impl;

import java.util.List;

import javax.servlet.http.Part;

import com.dao.Dao;
import com.dao.impl.DaoImpl;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.service.Service;
import com.shared.File;
import com.shared.Folder;
import com.shared.User;

public class ServiceImpl implements Service {
	private Dao dao = new DaoImpl();
	private String acCred="DefaultEndpointsProtocol=https;AccountName=mywebsitestorage91;AccountKey=QAmyJNW+8klNXNuY/3i"
			+ "m0gPZv1E7e57MsrWsuv6ssVSXlEhRnV4//5bEziC36CYnZCJm4D0OfflwZTjJ377YpA==;EndpointSuffix=core.windows.net";
	@Override
	public User getUser(String email, String password) {
		// TODO Auto-generated method stub
		User user = null;
		User temp = dao.geUser(email);
		if(temp != null && temp.getPassword().equals(password)) 
		{
			user = temp;	
		}
		return user;
	}
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		dao.addUser(user);
	}
	@Override
	public void addFolder(Folder folder) {
		// TODO Auto-generated method stub
		dao.addFolder(folder);
	}
	@Override
	public void addFile(File file) {
		// TODO Auto-generated method stub
		dao.addFile(file);
	}
	@Override
	public List<Folder> getFolders(Folder folder) {
		// TODO Auto-generated method stub
		return dao.getSubFolders(folder);
	}
	@Override
	public List<File> getFiles(Folder folder) {
		// TODO Auto-generated method stub
		return dao.getFolderFiles(folder);
	}
	@Override
	public List<Folder> getRootFolders() {
		// TODO Auto-generated method stub
		return dao.getRootFolders();
	}

	public String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	      if (cd.trim().startsWith("filename")) {
	        String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	        return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	      }
	    }
	    return null;
	  }
	
	public boolean uploadFile(Part part)
	{
		boolean flag = false;
		CloudStorageAccount az = null;
		try {
			az = CloudStorageAccount.parse(acCred);
			CloudBlobClient blobClient = az.createCloudBlobClient();
			CloudBlobContainer container = blobClient.getContainerReference("$web");
			CloudBlockBlob blob = container.getBlockBlobReference(getFilename(part));
			blob.upload(part.getInputStream(), part.getSize());
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public Folder getFolder(int fid) {
		// TODO Auto-generated method stub
		return dao.getFolder(fid);
	}
	@Override
	public void deleteFolder(Folder folder) {
		// TODO Auto-generated method stub
		dao.deleteFolder(folder);
	}
	@Override
	public void deleteFile(File file) {
		// TODO Auto-generated method stub
		dao.deleteFile(file);
	}
	@Override
	public File getFile(int id) {
		// TODO Auto-generated method stub
		return dao.getFile(id);
	}
}
