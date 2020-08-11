package com.hawer.app.dto;

public class ApplicationDto {

	private String guid_app;
	private String guid_production;
	private String name;
	private String description;
	private boolean status;
	
	public String getGuid_app() {
		return guid_app;
	}
	public void setGuid_app(String guid_app) {
		this.guid_app = guid_app;
	}
	public String getGuid_production() {
		return guid_production;
	}
	public void setGuid_production(String guid_production) {
		this.guid_production = guid_production;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
