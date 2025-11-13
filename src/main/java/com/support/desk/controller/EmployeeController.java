package com.support.desk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.support.desk.dto.TicketDetailsUpdatedDto;
import com.support.desk.dto.TicketDto;
import com.support.desk.dto.UserRegistrationDto;
import com.support.desk.service.EmployeeService;
import com.support.desk.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    //this is controller class for employee related endpoints ,where emplyees can be registered upadarete tickets and add comments to tickets.
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private EmployeeService employeeService;
	
	// 
	
	@PostMapping("/registe/employee")
	public ResponseEntity<?> registerEmployee( @Valid @RequestBody UserRegistrationDto registrationDto)
	{
		employeeService.registerEmployee(registrationDto);
		return ResponseEntity.ok().body("Employee registered successfully");
	}
	
	@GetMapping("/tickets/{userId}")
	public ResponseEntity<List<TicketDto>> getAssignedtickets(@PathVariable Long userId)
	{
		return ResponseEntity.ok().body(ticketService.getTicketsByAgentId(userId));
	}
	
	@PutMapping("/ticket/update/")
	public ResponseEntity<String> updateTicketDetails(@Valid @RequestBody TicketDetailsUpdatedDto ticketDetailsUpdatedDto)
	{
		return ResponseEntity.ok(ticketService.updateTicket(ticketDetailsUpdatedDto));
	}
	
}
