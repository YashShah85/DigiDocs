package com.dao;

import java.util.*;

import javax.persistence.EntityManager;

import com.shared.*;

public interface Dao {
	EntityManager getEntityManager();
	User geUser(String email);
	void addUser(User user);
	void addFolder(Folder folder);
	void addFile(File file);
	List<Folder> getSubFolders(Folder folder);
	List<File> getFolderFiles(Folder folder);
	List<File> getUserOwnedFiles(User user);
}
