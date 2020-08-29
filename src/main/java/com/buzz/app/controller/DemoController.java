package com.buzz.app.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buzz.app.service.DemoService;

@RestController
@RequestMapping("/maindemo")
public class DemoController {

	@Autowired
	private DemoService demoService;
		
	
	@GetMapping("/hello")
	public HashMap<String,Object> hello(){
				
		return this.demoService.getAllEmplpyee();
		//return this.demoService.getEmplpyee();
	}
	
	@GetMapping("/bye")
	public HashMap<String,Object> bye(){

		return this.demoService.selectDynamic();
	}
	
	@GetMapping("/update")
	public HashMap<String,Object> update(){

		return this.demoService.updateDynamic();
	}
	
	@GetMapping("/inf")
	public HashMap<String,Object> inf(){

		return this.demoService.interfacedb();
	}

	@GetMapping("/count")
	public HashMap<String,Object> count(){

		return this.demoService.count();
	}
	
}
