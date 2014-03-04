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
	private String title;
	private String location;
	private int playtime;
	private int vrgRating;
	
	public Song(User u, String s1, String s2){
		interpret = u;
		title = s1;
		location = s2;
		vrgRating = 0;
		setPlaytime();
	}
	public User getInterpret() {
		return interpret;
	}

	public String getTitle() {
		return title;
	}

	public int getPlaytime() {
		return playtime;
	}
	private void setPlaytime() {
		//Berechnung der Playtime der zugehörigen Datei
	}

}
