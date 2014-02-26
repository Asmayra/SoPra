package org.model;

import javax.persistence.Entity;

@Entity
public class Artist extends UserDecorator {

	public Artist(User u) {
		super(u);
	}

}
