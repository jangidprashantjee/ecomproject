package com.support.desk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.support.desk.dto.UserRegistrationDto;
import com.support.desk.model.User;
import com.support.desk.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User registerUser(UserRegistrationDto userRegistrationDto) {
		User user = new User();
		user.setUserName(userRegistrationDto.getUsername());
		user.setEmail(userRegistrationDto.getEmail());
		user.setPassword(userRegistrationDto.getPassword());
		user.setFullName(userRegistrationDto.getFullname());
		user.setPhoneNo(userRegistrationDto.getPhoneNumber());
		user.setAge(userRegistrationDto.getAge());
		user.setGender(userRegistrationDto.getGender());
		return userRepository.save(user);
	}
}
