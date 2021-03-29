package com.tweetapp.tweetApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.tweetApplication.model.Tweet;
import com.tweetapp.tweetApplication.util.DaoQuery;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer>{

	@Query(value= DaoQuery.QUERY_GET_ALL_USER_TWEETS, nativeQuery = true)
	List<Tweet> findTweetByEmailId(String emailId);
}

