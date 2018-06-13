package com.shopping.services.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.UserDao;
import com.shopping.entity.User;
import com.shopping.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	public void addUser(User user) {
		userDao.saveUser(user);
		
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}


	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
     
}