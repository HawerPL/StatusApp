package com.hawer.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hawer.app.dto.ErrorDto;
import com.hawer.app.mappers.ErrorMapper;
import com.hawer.app.services.LogApplicationErrorService;

@CrossOrigin
@RestController
public class ErrorController {

	@Autowired
	LogApplicationErrorService errorSerivce;
	
	@Autowired
	ErrorMapper errorMapper;
	
	@GetMapping("errors/{guid_app}")
	public List<ErrorDto> getAllErrorsByGuidApp(@PathVariable("guid_app") String guid_app) {
		return errorMapper.toErrorDtoList(errorSerivce.getAllErrorsByGuidApp(guid_app));
	}
}
