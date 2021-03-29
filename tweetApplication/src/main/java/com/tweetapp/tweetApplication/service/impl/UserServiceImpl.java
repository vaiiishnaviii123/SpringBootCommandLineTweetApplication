package com.tweetapp.tweetApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweetApplication.dao.UserDao;
import com.tweetapp.tweetApplication.model.User;
import com.tweetapp.tweetApplication.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired 
	UserDao userDao ;

	@Override
	public User registerNewUser(User user) {
			
		return userDao.registerNewUser(user);
	}

	@Override
	public User userLogin(String emailId, String password) {
		
		User result = userDao.userLogin(emailId, password);
		
		return result;
	}

	@Override
	public Integer changePassword(String emailId, String oldPassword, String newPassword) {
				
		Integer result = userDao.changePassword(emailId, oldPassword, newPassword);
		
		return result;
	}

	@Override
	public User getUserByUserId(String emailId) {
		
		return userDao.getUserByUserId(emailId);
	}

	@Override
	public List<User> getAllUsers() {
	
		return userDao.getAllUsers();
	}

	@Override
	public User userLogout(User user) {
		
		
		return userDao.userLogout(user);
	}

}

