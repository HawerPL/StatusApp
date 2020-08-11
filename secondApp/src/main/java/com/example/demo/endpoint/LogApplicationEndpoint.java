package com.example.demo.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.demo.service.ExErService;
import com.hawer.app.ws.LogApplicationActivity;
import com.hawer.app.ws.ObjectFactory;
import com.hawer.app.ws.Response;

@Endpoint
public class LogApplicationEndpoint {

	@Autowired
	private ExErService service;

	private static final String NAMESPACE = "ws.app.hawer.com";
		
	@PayloadRoot(namespace = NAMESPACE, localPart = "LogApplicationActivity")
	@ResponsePayload
	public Response getActivity(@RequestPayload LogApplicationActivity request) {
		Response response = new Response();
		response.setMessage("aa");
		return response;
	}

}