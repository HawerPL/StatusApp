package com.hawer.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawer.app.accessingdata.ApplicationRepository;
import com.hawer.app.entity.Application;


@Service
public class ApplicationService {

	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	LogApplicationActivityService logAppActivityService;
	
	public List<Application> getApplicationsByGuidProduction(String guid_production){
		return applicationRepository.findApplicationsByGuidProduction(guid_production);
	}
	
	public Application getApplication(String guid_app){
		Application application = applicationRepository.findApplicationByGuid(guid_app);
		return application;
	}
	
	public void addApplication(Application application) {
		application.setGuid_app(UUID.randomUUID().toString());
		applicationRepository.save(application);
	}
	
	public void deleteApplication(String guid) {
		applicationRepository.deleteById(guid);
	}
	
	public void updateApplication(Application application){
		String guid = application.getGuid_app();
		String name = application.getName();
		String guid_production = application.getGuid_production();
		String description = application.getDescription();
		if(name.isBlank()) {
			name = applicationRepository.findApplicationByGuid(guid).getName();
		}
		if(guid_production.isBlank()) {
			guid_production = applicationRepository.findApplicationByGuid(guid).getGuid_production();
		}
		if(description.isBlank()) {
			description = applicationRepository.findApplicationByGuid(guid).getDescription();
		}
		application.setName(name);
		application.setGuid_production(guid_production);
		application.setDescription(description);
		applicationRepository.save(application);
	}
	public boolean existsApplication(String guid) {
		if(applicationRepository.existsById(guid))
			return true;
		else
			return false;
	}
}
