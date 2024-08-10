package com.group.sdf.dto;

public class JwtResponse {
	
	private String jwtToken;
	private String name;
	
	public String getJwtToken() {
		return jwtToken;
	}
	public String getName() {
		return name;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public void setName(String name) {
		this.name = name;
	}
}
