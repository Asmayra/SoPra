package org.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
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
	@ManyToOne
	private Genre parent;
	@OneToMany
	private LinkedList<Genre> subGenres;

	public Genre(){
		subGenres = new LinkedList<Genre>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Genre> getSubGenres() {
		return (LinkedList<Genre>) subGenres;
	}

	public void setSubGenres(LinkedList<Genre> subGenres) {
		this.subGenres = subGenres;
	}
	
	public void addSubGenre(Genre sub){
		subGenres.addLast(sub);
	}

	public Genre getParent() {
		return parent;
	}

	public void setParent(Genre parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString(){
		return name;
	}

}
