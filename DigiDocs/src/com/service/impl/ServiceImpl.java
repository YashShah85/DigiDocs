package com.service.impl;

import com.dao.Dao;
import com.dao.impl.DaoImpl;
import com.service.Service;
import com.shared.File;
import com.shared.Folder;
import com.shared.User;

public class ServiceImpl implements Service {
	private Dao dao = new DaoImpl();
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

}
