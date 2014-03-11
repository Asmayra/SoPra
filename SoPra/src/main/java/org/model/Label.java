package org.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Entity for Label
 * @author Philipp, Michael Pfennings
 *
 */
@Entity
@Table(name="LABEL_TABLE")
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int LabelId;
	private String name = "";
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User manager;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name="ARTIST_TABLE")
	private Set<User> artists = new HashSet<User>();

	public Label() {	}

	public String getName() {
		return name;
	}
	
	public int getId()
	{
		return LabelId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<User> getArtists()
	{
		return new ArrayList<User>(this.artists);
	}
	
	public void addArtist(User artist)
	{
		this.artists.add(artist);
	}
	
	public void removeArtist(User artist)
	{
		this.artists.remove(artist);
	}
	

	public boolean isArtist(User user)
	{
		return artists.contains(user);
	}
	
	public void setManager(User manager){
		this.manager=manager;
	}
	
	public User getManager(){
		return manager;
		
	}
}
