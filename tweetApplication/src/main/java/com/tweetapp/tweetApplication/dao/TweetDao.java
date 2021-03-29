package com.tweetapp.tweetApplication.dao;

import java.util.List;

import com.tweetapp.tweetApplication.model.Tweet;

public interface TweetDao {

	Tweet addTweet(Tweet tweet);
	List<Tweet> getUserAllTweets(String emailId);
	List<Tweet> getAllTweets();
}
