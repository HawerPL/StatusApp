package com.hawer.app.mappers;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.hawer.app.dto.ApplicationDto;
import com.hawer.app.entity.Application;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ApplicationMapper {

	Application applicationDtoToApplication(ApplicationDto applicationDto);
	ApplicationDto applicationToApplicationDto(Application application);
	
	List<Application> toApplicationList(List<ApplicationDto> applicationDtoList);
	List<ApplicationDto> toApplicationDtoList(List<Application> application);
	
}
