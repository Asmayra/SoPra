package org.model;

import javax.persistence.Entity;

@Entity
public class LabelManager extends UserDecorator {

	public LabelManager(User u) {
		super(u);
	}

}
