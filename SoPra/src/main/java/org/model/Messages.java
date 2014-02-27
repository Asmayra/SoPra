package org.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="MESSAGE_TABLE")
public class Messages {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int messageId;
	@OneToOne
	private User sender;
	@ManyToOne
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
