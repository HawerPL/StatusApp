package com.hawer.app.dto;

import java.util.Date;

public class ErrorDto {

	private Long id;
	private String guid_app;
	private String error;
	private Date date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGuid_app() {
		return guid_app;
	}
	public void setGuid_app(String guid_app) {
		this.guid_app = guid_app;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
