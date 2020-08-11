package com.example.demo.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.hawer.app.ws.ErrorResponse;
import com.hawer.app.ws.LogApplicationError;

@Endpoint
public class LogApplicationErrorEndpoint {

	private static final String NAMESPACE = "ws.app.hawer.com";
		
	@PayloadRoot(namespace = NAMESPACE, localPart = "LogApplicationError")
	@ResponsePayload
	public ErrorResponse getError(@RequestPayload LogApplicationError request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setError("Przetworzyłem Twój błąd");
		return errorResponse;
	}
}
