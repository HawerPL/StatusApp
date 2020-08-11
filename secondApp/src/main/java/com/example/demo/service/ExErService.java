package com.example.demo.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawer.app.ws.LogApplicationActivity;

@Service
public class ExErService {

	@Autowired
	ErrorService errorService;

	Random rand = new Random();

	int array[] = new int [5];
	
	public void generateError() {

		int num = rand.nextInt(3);

		switch (num) {
		case 0:
			try {
				int a = 5;
				a = 5 / 0;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				errorService.sendError(e.getMessage());
			}
			break;
		case 1:
			try {
				int k = array[9];
			} catch (Exception e) {
				System.out.println(e.getMessage());
				errorService.sendError(e.getMessage());
			}
			break;
		case 2:
			try {
				String a = "aaa";
				a.charAt(19);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				errorService.sendError(e.getMessage());
			}
			break;
		}

	}

}
