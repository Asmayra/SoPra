package org.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="PLAYLIST_TABLE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int playlistId;
	@ManyToOne
	private User owner;
	@ManyToMany
	private Collection<Song> songs;
	private String name;
	
	public Playlist (User u){
		owner = u;
	}
	
	public User getOwner(){
		return this.owner;
	}
	
	public Collection<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}	
	public int getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
	
	


}
