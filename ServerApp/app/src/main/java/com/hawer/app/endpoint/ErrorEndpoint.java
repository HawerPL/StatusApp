package com.hawer.app.endpoint;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.hawer.app.ws.ErrorResponse;
import com.hawer.app.entity.Error;
import com.hawer.app.services.LogApplicationErrorService;
import com.hawer.app.ws.LogApplicationError;

@Endpoint
public class ErrorEndpoint {

	@Autowired
	LogApplicationErrorService errorService;
	
	private static final String NAMESPACE = "ws.app.hawer.com";
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "LogApplicationError")
	@ResponsePayload
	public ErrorResponse getError(@RequestPayload LogApplicationError request) {
		
		Date date = new Date();
		
		ErrorResponse errorResponse = new ErrorResponse();
		
		Error error = new Error();
		error.setDate(date);
		error.setGuid_app(request.getGuidApp());
		error.setError(request.getError());
		
		errorService.addError(error);
		
		errorResponse.setError("Ok");
		errorResponse.setGuidApp(request.getGuidApp());
		errorResponse.setGuidProduction(request.getGuidProduction());
		return errorResponse;
	}
}
