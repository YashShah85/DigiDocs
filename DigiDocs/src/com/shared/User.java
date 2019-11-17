package com.shared;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String name;

	private String password;

	@Lob
	private byte[] profilepic;

	private int status;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="user")
	private List<File> files1;

	//bi-directional many-to-one association to Folder
	@OneToMany(mappedBy="user")
	private List<Folder> folders;

	//bi-directional many-to-many association to File
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
		name="user_file_access"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="file_id")
			}
		)
	private List<File> files2;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getProfilepic() {
		return this.profilepic;
	}

	public void setProfilepic(byte[] profilepic) {
		this.profilepic = profilepic;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<File> getFiles1() {
		return this.files1;
	}

	public void setFiles1(List<File> files1) {
		this.files1 = files1;
	}

	public File addFiles1(File files1) {
		getFiles1().add(files1);
		files1.setUser(this);

		return files1;
	}

	public File removeFiles1(File files1) {
		getFiles1().remove(files1);
		files1.setUser(null);

		return files1;
	}

	public List<Folder> getFolders() {
		return this.folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public Folder addFolder(Folder folder) {
		getFolders().add(folder);
		folder.setUser(this);

		return folder;
	}

	public Folder removeFolder(Folder folder) {
		getFolders().remove(folder);
		folder.setUser(null);

		return folder;
	}

	public List<File> getFiles2() {
		return this.files2;
	}

	public void setFiles2(List<File> files2) {
		this.files2 = files2;
	}

}