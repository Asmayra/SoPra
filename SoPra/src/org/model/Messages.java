package org.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
public class Messages {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int messageId;
	
	private User sender;
	private User[] recipient;
	private String content;
	
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User[] getRecipient() {
		return recipient;
	}
	public void setRecipient(User[] recipient) {
		this.recipient = recipient;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
