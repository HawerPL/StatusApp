package com.hawer.app.mappers;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.hawer.app.dto.ErrorDto;
import com.hawer.app.entity.Error;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ErrorMapper {
	
	 	Error errorDtoToError(ErrorDto errorDto);
		ErrorDto errorToErrorDto(Error error);
		
		List<Error> toErrorList(List<ErrorDto> errorDtoList);
		List<ErrorDto> toErrorDtoList(List<Error> errorList);
}
