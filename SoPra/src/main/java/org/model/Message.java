package org.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Entity for Messages
 * @author Philipp, Michael Pfennings, Mattias Schoenke
 *
 */
@Entity
@Table(name="MESSAGE_TABLE")
public class Message implements Comparable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int messageId;
	@OneToOne
	private User sender;
//	@ManyToOne
//	private User[] recipient;
	private String content = "";
	private String subject = "";
	private Date date;
	
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
		return date.toString();
	}
	public void setDate(String date) {
		Date dateobject;
		try {
			dateobject = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse(date);
		} catch (ParseException e) {
			dateobject = null;
			e.printStackTrace();
		}
		this.date = dateobject;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
