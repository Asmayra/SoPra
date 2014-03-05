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
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int messageId;
	@OneToOne
	private User sender;
//	@ManyToOne
//	private User[] recipient;
	private String content;
	private String subject;
	private String date;
	
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
//	public User[] getRecipient() {
//		return recipient;
//	}
//	public void setRecipient(User[] recipient) {
//		this.recipient = recipient;
//	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getMessageId(){
		return this.messageId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
