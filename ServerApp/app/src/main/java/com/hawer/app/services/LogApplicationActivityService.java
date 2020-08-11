package com.hawer.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawer.app.accessingdata.ApplicationRepository;

@Service
public class LogApplicationActivityService {
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	public void updateActivity(boolean status, String guid_app){
		applicationRepository.updateApplicationStatus(status, guid_app);
	}
}
