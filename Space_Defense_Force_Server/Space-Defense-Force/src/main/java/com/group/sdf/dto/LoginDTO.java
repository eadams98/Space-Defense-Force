package com.group.sdf.dto;


public class LoginDTO {
	private String username;
	private String password;
	private String newPassword;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
