package com.support.desk.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ticket_comments")
public class TicketComment {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false,length = 1000)
	private String content;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "ticket_id",nullable = false)
	@JsonBackReference("ticket-comments")
	private Ticket ticket;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;

}
