package com.support.desk.dto;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String username;
	private String email;
	private String fullname;
	private String phoneNumber;
	private String employeeCode;
	private String department;
}
