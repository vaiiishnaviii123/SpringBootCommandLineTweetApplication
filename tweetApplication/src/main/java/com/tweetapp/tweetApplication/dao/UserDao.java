package com.tweetapp.tweetApplication.dao;

import java.util.List;

import com.tweetapp.tweetApplication.model.User;

public interface UserDao {

	User registerNewUser(User user);
	User userLogin(String emailId, String password);
	Integer changePassword(String emailId, String oldPassword, String newPassword);
	List<User> getAllUsers();
	User getUserByUserId(String emailId);
	User userLogout(User user);
}
