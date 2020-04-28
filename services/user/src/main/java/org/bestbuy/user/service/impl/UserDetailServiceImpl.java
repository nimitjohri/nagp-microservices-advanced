package org.bestbuy.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bestbuy.user.entity.User;
import org.bestbuy.user.exception.UserNotFoundException;
import org.bestbuy.user.models.UserDTO;
import org.bestbuy.user.repository.UserRepository;
import org.bestbuy.user.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<UserDTO> getUsers() {
		List<UserDTO> userDTOs = new ArrayList<>();
		List<User> users = new ArrayList<>();
		users = userRepository.findAll();
		users.forEach(u -> {
			UserDTO userDTO = new UserDTO(u.getUserId(), u.getName(), u.getEmailAddress(), u.getAddress());
			userDTOs.add(userDTO);
		});
		return userDTOs;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = new User();
		user = userRepository.getOne(userId);

		if (user != null) {
			UserDTO userDTO = new UserDTO(user.getUserId(), user.getName(), user.getEmailAddress(), user.getAddress());
			return userDTO;
		}
		throw new UserNotFoundException(userId);
	}
	

}
