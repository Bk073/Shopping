package com.shopping.dao;



import java.util.List;

import com.shopping.entity.User;

public interface UserDao {
	User findByUserName(String username);
	public void saveUser(User user);
	List<User> getAllUsers();
}
