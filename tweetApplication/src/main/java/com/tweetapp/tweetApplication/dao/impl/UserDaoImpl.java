package com.tweetapp.tweetApplication.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tweetapp.tweetApplication.dao.UserDao;
import com.tweetapp.tweetApplication.model.User;
import com.tweetapp.tweetApplication.repository.TweetRepository;
import com.tweetapp.tweetApplication.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerNewUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User userLogin(String emailId, String password) {
		
		
		User user = getUserByUserId(emailId);
		
		if(user != null && user.getPassword().equals(password)) {
			
			user.setIsLoggedIn(true);
			userRepository.save(user);
			
			return user;
		}else {
			return null;
		}
	}

	@Override
	public Integer changePassword(String emailId, String oldPassword, String newPassword) {
	
		User user = getUserByUserId(emailId);
		Integer result = 0;
		
		if(user.getPassword().equals(oldPassword)) {
			
			result = userRepository.updateUserPassowrd(newPassword, emailId);
		}
		
		return result;
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUserByUserId(String emailId) {
		
		return userRepository.findById(emailId).get();
	}

	@Override
	public User userLogout(User user) {		
		user.setIsLoggedIn(false);
		return userRepository.save(user);
	}

}
