package com.tweetapp.tweetApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweetapp.tweetApplication.model.User;
@Service
public interface UserService {

	User registerNewUser(User user);
	User userLogin(String emailId,String password);
	Integer changePassword(String emailId, String oldPassword, String newPassword);
	User getUserByUserId(String emailId);
	List<User> getAllUsers();
	User userLogout(User user);
}
