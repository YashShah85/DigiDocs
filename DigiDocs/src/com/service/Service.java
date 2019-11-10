package com.service;

import com.shared.*;

public interface Service {
	User getUser(String email, String password);
	void addUser(User user);
	void addFolder(Folder folder);
	void addFile(File file);
}
