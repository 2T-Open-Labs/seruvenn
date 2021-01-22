package com.ikite.seruvenn.DemoTriggerMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ikite.seruvenn.DemoTriggerMS.service.DenemeService;

@Controller
public class DenemeController {

	@Autowired
	DenemeService denemeService;
	
	@GetMapping("/dene")
	@ResponseBody
	public String dene() {
		
		//deneme için Sample tipinde Saga çağırıyoruz
		return denemeService.callSampleSaga();
		
	}
	
}
