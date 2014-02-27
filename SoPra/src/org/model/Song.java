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
<<<<<<< HEAD
	//private User interpret;
=======
	private Artist interpret;
>>>>>>> 0c2af9917cfb9b1ca2f4125451c64d2e8ce27ad3
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
