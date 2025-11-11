package com.support.desk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.support.desk.dto.UserRegistrationDto;
import com.support.desk.model.User;
import com.support.desk.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;
	@Transactional
	public User registerAdmin(UserRegistrationDto registrationDto) {
		
		User user = new User();
		user.setUserName(registrationDto.getUsername());
		user.setPassword(registrationDto.getPassword());
		user.setEmail(registrationDto.getEmail());
		user.setFullName(registrationDto.getFullname());
		user.setPhoneNo(registrationDto.getPhoneNumber());
		user.setGender(registrationDto.getGender());
		user.setAge(registrationDto.getAge());
		return userRepository.save(user);
	}
}
