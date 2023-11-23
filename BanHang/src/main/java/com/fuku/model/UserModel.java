package com.fuku.model;

public class UserModel {

	private int userID;
	private String username;
	private String password;
	private int isSeller;
	private int isAdmin;

	public UserModel() {

	}

	public UserModel(int userID, String username, String password, int isSeller, int isAdmin) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.isSeller = isSeller;
		this.isAdmin = isAdmin;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int isSeller() {
		return isSeller;
	}

	public void setSeller(int isSeller) {
		this.isSeller = isSeller;
	}

	public int isAdmin() {
		return isAdmin;
	}

	public void setAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

}
