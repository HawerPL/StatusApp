package com.hawer.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hawer.app.dto.ApplicationDto;
import com.hawer.app.exception.ServiceException;
import com.hawer.app.mappers.ApplicationMapper;
import com.hawer.app.services.ApplicationService;

@CrossOrigin
@RestController
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	ApplicationMapper applicationMapper;

	@GetMapping("/applications/{guid}")
	public List<ApplicationDto> getApplicationForProductions(@PathVariable("guid") String guid_production)
			throws ServiceException {
		List<ApplicationDto> applicationList = applicationMapper
				.toApplicationDtoList(applicationService.getApplicationsByGuidProduction(guid_production));
		return applicationList;
	}

	@GetMapping("/application/{guid}")
	public ApplicationDto getApplication(@PathVariable("guid") String guid_app) throws ServiceException {
		try {
			if (applicationService.existsApplication(guid_app)) {
				ApplicationDto applicationDto = applicationMapper
						.applicationToApplicationDto(applicationService.getApplication(guid_app));
				return applicationDto;
			} else {
				throw new ServiceException("Application not found");
			}
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@PostMapping("/application")
	public void addApplication(@RequestBody ApplicationDto applicationDto) throws ServiceException {
		try {
			if (applicationDto.getName().equals("")) {
				throw new ServiceException("Aplikacja musi mieć nazwę");
			}
			applicationService.addApplication(applicationMapper.applicationDtoToApplication(applicationDto));
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@DeleteMapping("/application/{guid}")
	public void deleteApplication(@PathVariable("guid") String guid) throws ServiceException {
		try {
			if (applicationService.existsApplication(guid)) {
				applicationService.deleteApplication(guid);
			} else {
				throw new ServiceException("Application not found");
			}
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@PutMapping("/application")
	public void updateApplication(@RequestBody ApplicationDto applicationDto) throws ServiceException {
		applicationService.updateApplication(applicationMapper.applicationDtoToApplication(applicationDto));

	}

}
