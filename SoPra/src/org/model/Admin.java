package org.model;

import javax.persistence.Entity;

@Entity
public class Admin extends UserDecorator {

	private Tickets tickets;

}
