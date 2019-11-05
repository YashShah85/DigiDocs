package com.shared;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the folder database table.
 * 
 */
@Entity
@NamedQuery(name="Folder.findAll", query="SELECT f FROM Folder f")
public class Folder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="folder_id")
	private int folderId;

	@Column(name="folder_name")
	private String folderName;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="folder")
	private List<File> files;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="owner_id")
	private User user;

	//bi-directional many-to-one association to Folder
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Folder folder;

	//bi-directional many-to-one association to Folder
	@OneToMany(mappedBy="folder")
	private List<Folder> folders;

	public Folder() {
	}

	public int getFolderId() {
		return this.folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public File addFile(File file) {
		getFiles().add(file);
		file.setFolder(this);

		return file;
	}

	public File removeFile(File file) {
		getFiles().remove(file);
		file.setFolder(null);

		return file;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<Folder> getFolders() {
		return this.folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public Folder addFolder(Folder folder) {
		getFolders().add(folder);
		folder.setFolder(this);

		return folder;
	}

	public Folder removeFolder(Folder folder) {
		getFolders().remove(folder);
		folder.setFolder(null);

		return folder;
	}

}