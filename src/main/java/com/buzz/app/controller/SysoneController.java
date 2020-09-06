package com.buzz.app.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buzz.app.service.CustService;

@RestController
@RequestMapping("/cust")
public class SysoneController {

	@Autowired
	private CustService custService;
	
	@GetMapping("/info")
	public HashMap<String,Object> info(){
				
		HashMap<String, Object> result = new  HashMap<String, Object>();
		result.put("status", 0);
		result.put("data", "info");
		
		return result;		
	}
	
	@GetMapping("/find")
	public HashMap<String,Object> findCustomer(){
				
		return custService.findCustomer();
	}
	
	@PostMapping("/new")
	public HashMap<String,Object> newCustomer(){
				
		return custService.newCustomer();
	}
}
