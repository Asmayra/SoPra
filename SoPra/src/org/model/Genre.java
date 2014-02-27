package org.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TICKET_TABLE")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int genreId;
	private String name;
	@OneToMany
	private Collection<Genre> subGenres;

	public Genre() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Genre> getSubGenres() {
		return subGenres;
	}

	public void setSubGenres(List<Genre> subGenres) {
		this.subGenres = subGenres;
	}
	
	

}
