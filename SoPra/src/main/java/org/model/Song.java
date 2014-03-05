package org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SONG_TABLE")
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int songId;
	private String interpret;
	private String title;
	private String location;
	@ManyToOne
	private Album album;
	private int playtime;
	private double vrgRating;
	private int ratingCount;

	public Song(String u, String s1, String s2, Album a) {
		interpret = u;
		title = s1;
		location = s2;
		vrgRating = 0;
		ratingCount = 0;
		album = a;
		setPlaytime();
	}

	public Song(String u, String s1, String s2) {
		interpret = u;
		title = s1;
		location = s2;
		vrgRating = 0;
		ratingCount = 0;
		album = null;
		setPlaytime();
	}

	public String getInterpret() {
		return interpret;
	}

	public String getTitle() {
		return title;
	}

	public int getPlaytime() {
		return playtime;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album a) {
		album = a;
	}

	public int getVrgRating() {
		int r1 = (int) Math.round(vrgRating);
		if ((vrgRating-r1)<0.5){
			return r1;
		}
		return (r1+1);
		
	}

	public void setVrgRating(int newrating) {
		ratingCount++;
		vrgRating = (vrgRating + (double) newrating) / ratingCount;
	}

	private void setPlaytime() {
		// Berechnung der Playtime der zugehörigen Datei
	}

}
