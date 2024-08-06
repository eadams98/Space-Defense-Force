package com.group.sdf.dto;


public class LoginDTO {
	private String commanderEmail;
	private String commanderPassword;
	private String commanderNewPassword;
	
	public String getCommanderEmail() {
		return commanderEmail;
	}
	public String getCommanderPassword() {
		return commanderPassword;
	}
	public String getCommanderNewPassword() {
		return commanderNewPassword;
	}
	public void setCommanderEmail(String commanderEmail) {
		this.commanderEmail = commanderEmail;
	}
	public void setCommanderPassword(String commanderPassword) {
		this.commanderPassword = commanderPassword;
	}
	public void setCommanderNewPassword(String commanderNewPassword) {
		this.commanderNewPassword = commanderNewPassword;
	} 
}
