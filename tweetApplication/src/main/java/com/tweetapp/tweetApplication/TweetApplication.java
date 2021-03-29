package com.tweetapp.tweetApplication;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tweetapp.tweetApplication.exception.IncorrectLoginCredentialsException;
import com.tweetapp.tweetApplication.exception.UserRegistrationException;
import com.tweetapp.tweetApplication.model.Tweet;
import com.tweetapp.tweetApplication.model.User;
import com.tweetapp.tweetApplication.service.TweetService;
import com.tweetapp.tweetApplication.service.UserService;

@SpringBootApplication
public class TweetApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(TweetApplication.class);
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private  TweetService tweetService;
	
	public static void main(String[] args) {
		SpringApplication.run(TweetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	System.out.println("\n Welcome to Tweet Application!\n");
		
		int choice = 0;
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			do {
				
				System.out.println("\nPlease select your choice:\n 1.Register \n 2.Login \n 3.Forgot Password \n 4.Exit");
				choice = sc.nextInt();
				
				switch(choice) {
				
				case 1:{
					System.out.println("--: User Registration :-- \n ");
					
					System.out.println("Please enter the required information.");
					String firstName, lastName, gender, dob, password, emailId;
					
					boolean isLoggedIn = false;
					
					System.out.println("First Name:");
					firstName = sc.next();
					
					System.out.println("Last Name: ");
					lastName = sc.next();
					
					System.out.println("Gender: ");
					gender = sc.next();
					
					System.out.println("Date of birth: Please enter in folloeing format - MM.DD.YYYY");
					dob = sc.next();
					SimpleDateFormat sdf = new SimpleDateFormat(dob);
					java.util.Date date = sdf.parse(dob);
					java.sql.Date dateOfBirth = new java.sql.Date(date.getTime());
					
					System.out.println("Email ID: ");
					emailId = sc.next();
					
					System.out.println("Password: ");
					password = sc.next();
					
					User user = new User(emailId, firstName, lastName, gender, dateOfBirth, password, isLoggedIn);
					
					User registeredUser = userService.registerNewUser(user);
					
					if(registeredUser == null) {
						throw new UserRegistrationException("User registration failed.");
					}else {
						System.out.println("User registered successfully!\n");
					}
					break;
				}
				case 2: {
					
					String emailId, password;
					
					System.out.println("Enter your credentials to login.\n");
					
					System.out.println("Username: ");
					emailId = sc.next();
					
					System.out.println("Password: ");
					password = sc.next();
					
					User loggedInUser = userService.userLogin(emailId, password);
					
					if(loggedInUser != null) {
						Integer loggedInUserChoice = 0;
						
						System.out.println("Login Successful! \n");
						do {
							
							System.out.println("\n Please select your choice: \n 1.Post a tweet \n 2.View my tweets \n 3.View All tweets \n 4.View all users \n 5.Reset password \n 6.Logout ");
							loggedInUserChoice = sc.nextInt();
							
							switch(loggedInUserChoice) {
							
							case 1: {
								String tweetMessage;
								
								System.out.println("Post A Tweet: ");
								sc.nextLine();
								tweetMessage = sc.nextLine();
								
								Tweet tweet = new Tweet();
								tweet.setEmailId(loggedInUser);
								tweet.setTweetMessage(tweetMessage);
								
								Tweet tweetResult = tweetService.newTweet(tweet);
								
								if(tweetResult != null) {
									System.out.println(tweetResult.getTweetMessage() + " posted successfully!");
								}else {
									System.out.println("Error occured while posting tweet please try again");
								}
								break;
							}
							case 2: {
								System.out.println("Tweets of user: "+ emailId);
								List<Tweet> tweets = tweetService.viewUsersTweets(emailId);
								
								if (tweets != null) {
									System.out.println("Tweet count: "+ tweets.size());
									for(Tweet tweet : tweets) {
										System.out.println(tweet.getTweetMessage());
									}
								}else {
									System.out.println("No tweets available for user"+ emailId);
								}
								
								break;
								
							}
							case 3:{
								System.out.println(emailId + " you are viewing tweets of all users!");
								
								List<Tweet> tweets = tweetService.viewAllTweets();
								
								if(tweets != null) {
									System.out.println("Tweet count: "+ tweets.size());
									for(Tweet tweet : tweets) {
										System.out.println("Tweet by: "+ tweet.getUserId()+ "\t Tweet: " + tweet.getTweetMessage());
									}
								}else {
									System.out.println("No tweets present in the application please try again later!");
								}
							
								break;
							}
							case 4: {
								System.out.println(emailId + " you are viewing all users of this application!");
								
								List<User> users = userService.getAllUsers();
								
								if(users != null) {
									System.out.println("Total users count: "+ users.size());
									
									for(User user : users) {
										System.out.println(user.getEmailId());
									}
								}else{
									System.out.println("No users present in the application please try again later!");
								}
								break;
								
								}
							case 5: {
								String oldPassword, newPassword;
								
								System.out.println("\n To reset password please enter the below details: ");
								
								System.out.println("Enter oldPassword: ");
								oldPassword = sc.next();
								
								System.out.println("Enter new password: ");
								newPassword = sc.next();
								
								Integer resetResult = userService.changePassword(emailId, oldPassword, newPassword);
								
								if(resetResult == 1) {
									System.out.println("Password reset successful!\n");
								}else {
									System.out.println("Password reset failed please try again.");
								}
								
								break;
							}
							case 6: {
								
								User user = userService.userLogout(loggedInUser);
								if(user != null) {
									System.out.println(user.getEmailId() + " logged out successfully!");
								}else {
									throw new Exception();
								}
								break;
							}
							}
						}while(loggedInUserChoice != 6);
					
					}else {
						throw new IncorrectLoginCredentialsException("Login failed, incorrect credentials entered.");
						
					}
					break;
				}
				case 3: {
					String emailId, oldPassword, newPassword;
					System.out.println("Forgot password? Please enter the below information");
					
					System.out.println("Email ID: ");
					emailId = sc.next();
					
					System.out.println("Please enter your old password");
					oldPassword = sc.next();
					
					System.out.println("Please enter the new password");
					newPassword = sc.next();
					
					Integer result = userService.changePassword(emailId, oldPassword, newPassword);
					System.out.println("Change password result"+ result);
					
					if(result == 0) {
						System.out.println("Changed password failed");
					}else {
						System.out.println("Password changed successfully!");
						
					}
					break;
				}
				case 4: break;
				}
			}while(choice!= 4);
			sc.close();
		}catch(Exception e) {
			LOG.error("Error:" + e.getMessage());
		}
		
	}
	

}
