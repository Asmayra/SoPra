package org.model;

import javax.persistence.Entity;


@Entity
public class UserDecorator implements User {
	private User user;
		
	public UserDecorator(User u){
		this.setUser(u);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
