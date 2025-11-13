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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.support.desk.dto.TicketDto;
import com.support.desk.dto.UserRegistrationDto;
import com.support.desk.model.TicketStatus;
import com.support.desk.service.AdminService;
import com.support.desk.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/registe/admin")
	public ResponseEntity<?> registerAdmin( @Valid @RequestBody UserRegistrationDto registrationDto)
	{
		adminService.registerAdmin(registrationDto);
		return ResponseEntity.ok().body("Admin registered successfully");
	}
	
	@GetMapping("/tickets")
	public ResponseEntity<List<TicketDto>> getAllTickets()
	{
		return ResponseEntity.ok().body(ticketService.getTicketsByStatus(TicketStatus.OPEN));
	}
	
	@GetMapping("/tickets/status/{status}")
	
	public ResponseEntity<List<TicketDto>> getTicketsByStatus(@PathVariable TicketStatus status)
	{
		return ResponseEntity.ok().body(ticketService.getTicketsByStatus(status));
	}
	
	@GetMapping("/tickets/department/{department}")
	public ResponseEntity<List<TicketDto>> getTicketsByDepartment(@PathVariable String department)
	{
		return ResponseEntity.ok().body(ticketService.getTicketsByDepartment(department));
	}
	
	@GetMapping("/tickets/agent/{agent}")
	public ResponseEntity<List<TicketDto>> getTicketsByAgent(@PathVariable Long agentId)
	{
		return ResponseEntity.ok().body(ticketService.getTicketsByAgentId(agentId));
	}
	
	@GetMapping("/tickets/count")
	public ResponseEntity<Long> getTotalTicketCount()
	{
		return ResponseEntity.ok().body(ticketService.getTotalActiveTicketCount());
	}
	
	@PutMapping("/ticket/assign/")
	public ResponseEntity<TicketDto> assignTicketToAgent(@RequestParam long ticketId, @RequestParam long agentId)
	{
		return ResponseEntity.ok(ticketService.assignTicket(ticketId, agentId));
	}
	
}
