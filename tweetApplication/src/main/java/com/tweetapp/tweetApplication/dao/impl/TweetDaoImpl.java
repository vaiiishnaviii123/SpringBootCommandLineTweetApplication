package com.tweetapp.tweetApplication.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tweetapp.tweetApplication.dao.TweetDao;
import com.tweetapp.tweetApplication.model.Tweet;
import com.tweetapp.tweetApplication.repository.TweetRepository;

@Repository
public class TweetDaoImpl implements TweetDao{
	
	@Autowired
	private TweetRepository tweetRepository; 

	@Override
	public Tweet addTweet(Tweet tweet) {
		return tweetRepository.save(tweet);
		
	}

	@Override
	public List<Tweet> getUserAllTweets(String emailId) {
		
		return tweetRepository.findTweetByEmailId(emailId);
		
	}

	@Override
	public List<Tweet> getAllTweets() {
		
		return tweetRepository.findAll();
	}

}

