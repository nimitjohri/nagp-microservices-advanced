package org.bestbuy.user.service;

import java.util.List;

import org.bestbuy.user.models.UserDTO;


public interface UserDetailService {

	public List<UserDTO> getUsers();
	
	public UserDTO getUserById(Integer userId);
}
