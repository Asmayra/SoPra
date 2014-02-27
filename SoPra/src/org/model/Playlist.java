package org.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int playlistId;
	private User owner;
	private ArrayList<Song> songs;
	private String name;
	


}
