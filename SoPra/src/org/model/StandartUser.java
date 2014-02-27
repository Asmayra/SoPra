package org.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StandartUser implements User {
	@Id
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private Date dob;
	private String city;
	private String country;
	

}
