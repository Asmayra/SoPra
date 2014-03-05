package org.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="LABEL_TABLE")
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int LabelId;
	private String name;
	@OneToMany
	private Set<User> manager;

	public Label() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getManager() {
		return new ArrayList<User>(this.manager);
	}

	public void setManager(List<User> manager) {
		this.manager = new TreeSet<User>(manager);
	}

}
