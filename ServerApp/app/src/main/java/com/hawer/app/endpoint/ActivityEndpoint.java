package com.hawer.app.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.hawer.app.services.LogApplicationActivityService;
import com.hawer.app.ws.LogApplicationActivity;
import com.hawer.app.ws.Response;

@Endpoint
public class ActivityEndpoint {

	@Autowired
	LogApplicationActivityService service;
	
	private static final String NAMESPACE = "ws.app.hawer.com";
	
	private static final Logger log = LoggerFactory.getLogger(ActivityEndpoint.class);
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "LogApplicationActivity")
	@ResponsePayload
	public Response getActivity(@RequestPayload LogApplicationActivity request) {
		Response response = new Response();
		response.setMessage("Ok, thx");	
		
		service.updateActivity(true , request.getGuidApp());
		changeStatus(request.getGuidApp());
		return response;
	}
	
	@Async
	public void changeStatus(String guid_app) {
		try {
			Thread.sleep(59000);
		} catch (InterruptedException e) {
			log.warn(e.getMessage());
		}
		service.updateActivity(false , guid_app);
	}
}
