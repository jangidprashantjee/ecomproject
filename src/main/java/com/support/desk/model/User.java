package com.support.desk.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private String email;
	private String password;
	private String gender;
	private String phoneNo;
	private String fullName;
	private int age;
	
	private String employeeCode;
	private String department;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	@JsonManagedReference("customer-ticket")
	private Set<Ticket>customerTickets = new HashSet<>();
	
	@OneToMany(mappedBy = "assignedAgent",cascade = CascadeType.ALL)
	@JsonManagedReference("agent-ticket")
	private Set<Ticket>assignedTickets = new HashSet<>();
	
	
	
	
}
