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

}
