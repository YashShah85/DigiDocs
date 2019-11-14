package com.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.dao.Dao;
import com.shared.File;
import com.shared.Folder;
import com.shared.User;

public class DaoImpl implements Dao {
	private EntityManagerFactory emf= javax.persistence.Persistence.createEntityManagerFactory("DigiDocs");
	EntityManager em = emf.createEntityManager();
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		User user = null;
		user = em.find(User.class, email);
		return user;
	}

	@Override
	public List<Folder> getSubFolders(Folder folder) {
		// TODO Auto-generated method stub 
		return folder.getFolders();
	}

	@Override
	public List<File> getFolderFiles(Folder folder) {
		// TODO Auto-generated method stub
		return folder.getFiles();
	}

	@Override
	public List<File> getUserOwnedFiles(User user) {
		// TODO Auto-generated method stub
		return user.getFiles2();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		System.out.println("user added");
	}

	@Override
	public void addFolder(Folder folder) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(folder);
		em.getTransaction().commit();
	}

	@Override
	public void addFile(File file) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(file);
		em.getTransaction().commit();
	}

	@Override
	public List<Folder> getRootFolders() {
		// TODO Auto-generated method stub
		String qstr = "Select f from Folder f where f.folder = null";
		Query query= em.createQuery(qstr);
		return query.getResultList();
	}

	@Override
	public Folder getFolder(int fid) {
		// TODO Auto-generated method stub
		return em.find(Folder.class,fid);
	}

	@Override
	public void deleteFolder(Folder folder) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.remove(folder);
		em.getTransaction().commit();
	}

	@Override
	public void deleteFile(File file) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.remove(file);
		em.getTransaction().commit();
	}

	@Override
	public File getFile(int id) {
		// TODO Auto-generated method stub
		return em.find(File.class, id);
	}

}
