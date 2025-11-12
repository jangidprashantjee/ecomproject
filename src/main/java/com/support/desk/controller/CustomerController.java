package com.support.desk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.support.desk.dto.TicketCommentDto;
import com.support.desk.dto.TicketDto;
import com.support.desk.dto.UserRegistrationDto;
import com.support.desk.service.TicketService;
import com.support.desk.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	

	@PostMapping("/register/customer")
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody UserRegistrationDto registrationDto)
	{
		userService.registerUser(registrationDto);
		return ResponseEntity.ok().body("Customer registered successfully");
	}
	
	@PostMapping("/raise/issue")
	public ResponseEntity<TicketDto> generateIssue( @Valid @RequestBody TicketDto ticketDto, @RequestParam Long userId)
	{
		TicketDto createdTicket = ticketService.createTicket(ticketDto, userId);
		return ResponseEntity.ok().body(createdTicket);
	}
	
	@GetMapping("/ticket/{userId}")
	public ResponseEntity<List<TicketDto>> getMyTickets(@PathVariable Long userId)
	{
		List<TicketDto> tickets = ticketService.getTicketsByUserId(userId);
		return ResponseEntity.ok().body(tickets);
	}
	
	@GetMapping("/comments/{ticketId}")
	public ResponseEntity<List<TicketCommentDto>> getcomments(@PathVariable Long ticketId)
	{
		List<TicketCommentDto> comments = ticketService.getCommentsByTicketId(ticketId);
		return ResponseEntity.ok().body(comments);
	}
}
