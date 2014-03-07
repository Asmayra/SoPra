package org.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
/**
 * Entity for Label
 * @author Philipp
 *
 */
@Entity
@Table(name="LABEL_TABLE")
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int LabelId;
	private String name;
	@OneToMany
	private Set<User> managers = new HashSet<User>();
	@OneToMany
	private Set<User> artists = new HashSet<User>();

	public Label() {	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getManagers() {
		return new ArrayList<User>(this.managers);
	}
	
	public void addManager(User manager)
	{
		this.managers.add(manager);
	}
	
	public void removeManager(User manager)
	{
		this.managers.remove(manager);
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
	
	public boolean isManager(User user)
	{
		return managers.contains(user);
	}

	public boolean isArtist(User user)
	{
		return artists.contains(user);
	}
}
