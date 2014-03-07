package org.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.JOptionPane;
/**
 * Entity for Playlists
 * @author Philipp
 *
 */
@Entity
@Table(name = "PLAYLIST_TABLE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Playlist implements Comparable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int playlistId;
	@ManyToOne
	private User owner;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Song> songs = new TreeSet<Song>();
	private String name;
	public Playlist() {

	}

	public Playlist(User u) {
		owner = u;

	}

	public User getOwner() {
		return this.owner;
	}

	public boolean contains(Song s) {
		if (songs.contains(s)) {
			return true;
		}
		return false;
	}

	public LinkedList<Song> getSongs() {
		LinkedList<Song> coll = new LinkedList<Song>(this.songs);
		return coll;
	}

	public void setSongs(List<Song> songs) {
		this.songs = new TreeSet<Song>(songs);
	}

	public void addSong(Song song){
		songs.add(song);
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

	private void testName(String name) throws IllegalArgumentException {
		if (name.equals("Favorits")) {
			throw new IllegalArgumentException();
		}
	}

	public void setFavorite() {
		this.name = ("Favorites");
	}

	public void setName(String name) {
		try {
			testName(name);
			this.name = name;
		} catch (IllegalArgumentException e) {
			JOptionPane
					.showMessageDialog(null,
							"Bitte geben sie Ihrer Playlist einen anderen Namen als \"Favorit\"");
		}

	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteSong(Song song){
		songs.remove(song);
	}

	public Playlist copyFriendFavorites(User u, Playlist p) {
		Playlist copyF = new Playlist(u);
		copyF.setSongs(p.getSongs());
		copyF.setName("Favorites from "+p.getOwner().getUsername());
		return copyF;
		
	}
}
