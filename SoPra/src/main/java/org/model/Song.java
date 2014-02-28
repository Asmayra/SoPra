package org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SONG_TABLE")
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int songId;	
	@ManyToOne
	private User interpret;
	private String name;
	private int playtime;
	
	
	public User getInterpret() {
		return interpret;
	}
	public void setInterpret(User interpret) {
		this.interpret = interpret;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlaytime() {
		return playtime;
	}
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

}