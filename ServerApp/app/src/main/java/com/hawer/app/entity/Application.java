package com.hawer.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="applications")
public class Application {

	@Id
	@Column(name = "guid_app")
	private String guid_app;
	@Column(name = "guid_production")
	private String guid_production;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "status")
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
