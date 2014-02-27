package org.model;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.LinkedList;
=======
>>>>>>> 0c2af9917cfb9b1ca2f4125451c64d2e8ce27ad3
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int playlistId;
<<<<<<< HEAD
	private String owner;		
	//@ManyToMany
	//private LinkedList<Song> songs;
=======
	private User owner;
	private List<Song> songs;
>>>>>>> 0c2af9917cfb9b1ca2f4125451c64d2e8ce27ad3
	private String name;
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
	
	


}
