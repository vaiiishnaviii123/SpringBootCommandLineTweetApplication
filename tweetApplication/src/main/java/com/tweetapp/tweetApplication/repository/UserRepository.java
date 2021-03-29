package com.tweetapp.tweetApplication.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.tweetApplication.model.Tweet;
import com.tweetapp.tweetApplication.model.User;
import com.tweetapp.tweetApplication.util.DaoQuery;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	List<Tweet> findTweetByEmailId(String emailId);
	
	
	
	@Transactional
	@Modifying
	@Query(value= DaoQuery.QUERY_UPDATE_PASSWORD, nativeQuery = true)
	Integer updateUserPassowrd(String password, String emailId);

}
