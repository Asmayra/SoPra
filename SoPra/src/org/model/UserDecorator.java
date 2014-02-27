package org.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class UserDecorator implements User {
	private User user;
	
	public UserDecorator(User u){
		this.user = u;
	}

	@Id
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public void setUsername(String username) {
		user.setUsername(username);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getFirstname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFirstname(String firstname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLastname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastname(String lastname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getDob() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDob(Date dob) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCity(String city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		
	}
	

}
