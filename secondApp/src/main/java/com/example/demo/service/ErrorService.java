package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.hawer.app.ws.ErrorResponse;
import com.hawer.app.ws.LogApplicationError;

@Service
public class ErrorService {

	String guid_app = "48199398-bc56-4680-9490-de6954af2871";
	String guid_production = "97d826ab-9543-4cbe-a0e1-fd4b7de8749f";
	String web_address = "http://localhost:8080/ws";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	public void sendError(String message) throws WebServiceIOException{
			LogApplicationError request = new LogApplicationError();
			request.setGuidApp(guid_app);
			request.setGuidProduction(guid_production);
			request.setError(message);
			ErrorResponse response = new ErrorResponse();
			template = new WebServiceTemplate(marshaller);
			try {
				response = (ErrorResponse) template.marshalSendAndReceive(web_address, request);
				System.out.println(response.getError());
			}catch(WebServiceIOException e) {
				System.out.println(e.getMessage());
			}
	}
}
