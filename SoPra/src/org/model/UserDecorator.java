package org.model;

import javax.persistence.Entity;


@Entity
public class UserDecorator implements User {
	private User user;

}
