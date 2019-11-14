package com.service;

import java.util.List;

import javax.servlet.http.Part;

import com.shared.*;

public interface Service {
	User getUser(String email, String password);
	void addUser(User user);
	void addFolder(Folder folder);
	void addFile(File file);
	List<Folder> getFolders(Folder folder);
	List<File> getFiles(Folder folder);
	List<Folder> getRootFolders();
	String getFilename(Part part);
	boolean uploadFile(Part part);
	Folder getFolder(int fid);
	void deleteFolder(Folder folder);
	void deleteFile(File file);
	File getFile(int id);
	User getUserForAccess(String email);
}
