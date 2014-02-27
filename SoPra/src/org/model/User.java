package org.model;

import java.util.Date;

import javax.persistence.Entity;


public interface User {
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public String getFirstname();
	public void setFirstname(String firstname);
	public String getLastname();
	public void setLastname(String lastname);
	public Date getDob();
	public void setDob(Date dob);
	public String getCity();
	public void setCity(String city);
	public String getCountry();
	public void setCountry(String country);

}
