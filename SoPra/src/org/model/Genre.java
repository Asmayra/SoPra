package org.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int genreId;
	private String name;
	private List<Genre> subGenres;

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
