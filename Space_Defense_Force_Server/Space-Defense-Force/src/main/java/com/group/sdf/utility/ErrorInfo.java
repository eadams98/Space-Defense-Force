package com.group.sdf.utility;

import java.time.LocalDateTime;

public class ErrorInfo {
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	private Integer errorCode;
	private LocalDateTime timestamp;
}
