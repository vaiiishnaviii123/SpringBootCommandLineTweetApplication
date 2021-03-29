package com.tweetapp.tweetApplication.util;

public class DaoQuery {

	public static final String QUERY_UPDATE_PASSWORD = "UPDATE tweetApp.User SET password=? where emailId=? ";

	public static final String QUERY_GET_ALL_USER_TWEETS = "SELECT * from tweetapp.tweet where emailId=?";
}
