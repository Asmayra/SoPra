package org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int songId;

	private Artist interpret;

	private String name;
	private int playtime;
	
	
	public Artist getInterpret() {
		return interpret;
	}
	public void setInterpret(Artist interpret) {
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
