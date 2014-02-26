package org.model;

import java.util.ArrayList;

import javax.persistence.Entity;
@Entity
public class Playlist {

	private User owner;
	private ArrayList<Song> songs;


}
