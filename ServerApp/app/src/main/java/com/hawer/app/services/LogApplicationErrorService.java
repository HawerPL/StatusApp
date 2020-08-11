package com.hawer.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawer.app.accessingdata.ErrorRepository;
import com.hawer.app.entity.Error;

@Service
public class LogApplicationErrorService {
	
	@Autowired
	ErrorRepository errorRepository;

	public List<Error> getAllErrorsByGuidApp(String guid_app){
		return errorRepository.findAllErrorsByGuidApp(guid_app);
	}
	
	public void addError(Error error) {
		errorRepository.save(error);
	}
}
