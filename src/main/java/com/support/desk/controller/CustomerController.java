package com.support.desk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@PostMapping("/register/customer")
	public void registerCustomer()
	{
		
		
	}
	
	@PostMapping("/raise/issue")
	public void raiseIssue()
	{
		
	}
	
	
	
}
