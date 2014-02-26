package org.model;

import javax.persistence.Entity;

@Entity
public class Admin extends UserDecorator {

	public Admin(User u) {
		super(u);
	}

	private Tickets tickets;

}
