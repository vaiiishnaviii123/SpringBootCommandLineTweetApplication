package com.tweetapp.tweetApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweetApplication.dao.TweetDao;
import com.tweetapp.tweetApplication.model.Tweet;
import com.tweetapp.tweetApplication.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetDao tweetDao;

	@Override
	public Tweet newTweet(Tweet tweet) {
		
		return tweetDao.addTweet(tweet);
		
	}

	@Override
	public List<Tweet> viewUsersTweets(String emailId) {
	
		return tweetDao.getUserAllTweets(emailId);
		
	}

	@Override
	public List<Tweet> viewAllTweets() {
		
		return tweetDao.getAllTweets();
	}
}
