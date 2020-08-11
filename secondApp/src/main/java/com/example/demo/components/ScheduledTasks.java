package com.example.demo.components;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.example.demo.service.ExErService;
import com.hawer.app.ws.LogApplicationActivity;
import com.hawer.app.ws.Response;

@Component
public class ScheduledTasks {

	String guid_app = "48199398-bc56-4680-9490-de6954af2871";
	String guid_production = "97d826ab-9543-4cbe-a0e1-fd4b7de8749f";
	String web_address = "http://localhost:8080/ws";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	ExErService exErService;
	
	@Scheduled(fixedRate = 60000 * 1)
	public void sentActivity() throws WebServiceIOException{
		log.info("The time is now {}", new Date());
		
		exErService.generateError();
		
		template = new WebServiceTemplate(marshaller);
		Response response = new Response();
		LogApplicationActivity request = new LogApplicationActivity();
		
    	request.setGuidApp(guid_app);
    	request.setGuidProduction(guid_production);
    	try {
    	response = (Response) template.marshalSendAndReceive(web_address, request);
    	//System.out.println(response.getMessage());
    	}catch(WebServiceIOException e) {
			System.out.println(e.getMessage());
    	}
  
	}
}
