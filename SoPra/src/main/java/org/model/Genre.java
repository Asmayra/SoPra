package org.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity for Genres
 * @author Philipp
 *
 */
@Entity
@Table(name="GENRE_TABLE")
public class Genre implements Comparable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int genreId;
	private String name = "";
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Genre> subGenres;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Genre parent;

	public Genre(){
		subGenres = new TreeSet<Genre>();
	}
	
	public Genre getParent() {
		return parent;
	}

	public void setParent(Genre parent) {
		this.parent = parent;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Genre> getSubGenres() {
		return new LinkedList<Genre>(this.subGenres);
	}

	public void setSubGenres(LinkedList<Genre> subGenres) {
		this.subGenres = new TreeSet<Genre>(subGenres);
	}
	
	public void addSubGenre(Genre sub){
		subGenres.add(sub);
	}
	
	@Override
	public String toString(){
		return name;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
