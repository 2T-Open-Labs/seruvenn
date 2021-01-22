package com.ikite.seruvenn.DemoConsumerMS.service;

import org.springframework.stereotype.Service;

@Service
public class DenemeService {

	public Long getUUID() {
		
		return System.currentTimeMillis(); //Long.valueOf(UUID.randomUUID().toString());
		
	}
	
}
