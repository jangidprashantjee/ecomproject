package com.support.desk.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {
 	@NotBlank
 	@Size(min = 4, max = 20)
	private String username;
 	
 	@NotBlank
 	@Size(min = 6, max = 20)
	private String email;
 	
 	@NotBlank
 	@Size(min = 6, max = 20)
	private String password;
 	
 	@Min(value = 18, message = "Age should must be at least 18")
	private int age;
 	
 	@NotBlank
	private String gender;
 	
 	@NotBlank
 	@Size(max=100)
	private String fullname;
 	
 	@Size(max=10)
	private String phoneNumber;
 	
 	@NotBlank
 	@Size(max=10)
 //  for employees
 	private String department;
 	private String employeeCode;
}
