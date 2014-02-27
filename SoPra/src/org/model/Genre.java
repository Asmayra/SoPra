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
	//private List<Genre> subGenres;

	public Genre() {
		// TODO Auto-generated constructor stub
	}
	
	

}
