package com.shopping.services;

import java.util.List;

import com.shopping.entity.User;

public interface UserService {
	void addUser(User user);

	List<User> getAllUsers();

	User findByUserName(String username);
}
