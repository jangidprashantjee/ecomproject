package com.support.desk.model;

import java.time.LocalDateTime;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="tickets")
public class Ticket {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,unique = true)
	private Long ticketId;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false, length = 1000)
	private String description;
	
	
	@Enumerated(EnumType.STRING)
	private TicketPriority priority;
	
	@Enumerated(EnumType.STRING)
	private TicketStatus status;
	
	
	private String department;
	
	@ManyToOne
	@JoinColumn(name="customer_id",nullable = false)
	@JsonBackReference("customer-tickets")
	private User customer;
	
	@ManyToOne
	@JoinColumn(name="agent_id")
	@JsonBackReference("agent-tickets")
	private User assignedAgent;
	
	
	
	
	private LocalDateTime creationTime;
	private LocalDateTime resolutionTime;
	
	@OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference("ticket-comments")
	private List<TicketComment>comments;
}
