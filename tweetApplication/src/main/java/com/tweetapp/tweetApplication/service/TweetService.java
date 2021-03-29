package com.tweetapp.tweetApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweetapp.tweetApplication.model.Tweet;
@Service
public interface TweetService {
	
	Tweet newTweet(Tweet tweet);
	List<Tweet> viewUsersTweets(String emailId);
	List<Tweet> viewAllTweets();

}
