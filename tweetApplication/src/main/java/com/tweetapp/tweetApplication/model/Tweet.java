package com.tweetapp.tweetApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tweet")
public class Tweet {

	@Id
	@Column(name="tweetId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tweetId;
	
	@Column(name="tweetMessage")
	private String tweetMessage;
	
	@ManyToOne
	@JoinColumn(name="emailId")
	private User emailId;

	public Tweet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getEmailId() {
		return emailId;
	}

	public void setEmailId(User emailId) {
		this.emailId = emailId;
	}

	public Integer getTweetId() {
		return tweetId;
	}

	public void setTweetId(Integer tweetId) {
		this.tweetId = tweetId;
	}

	public String getTweetMessage() {
		return tweetMessage;
	}

	public void setTweetMessage(String tweetMessage) {
		this.tweetMessage = tweetMessage;
	}
	
	public String getUserId() {
		return emailId.getEmailId();
	}

	@Override
	public String toString() {
		return "Tweet [tweetId=" + tweetId + ", tweetMessage=" + tweetMessage + ", emailId=" + emailId + "]";
	}
	
	
}



