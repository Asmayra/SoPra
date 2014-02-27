package org.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int LabelId;
	private String name;
	private List<User> manager;

	public Label() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getManager() {
		return manager;
	}

	public void setManager(List<User> manager) {
		this.manager = manager;
	}

}
