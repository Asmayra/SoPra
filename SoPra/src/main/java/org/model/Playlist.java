package org.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
import javax.persistence.Table;
import javax.swing.JOptionPane;

/**
 * Entity for Playlists
 * 
 * @author Philipp, Sebastian Roth
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
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Song> songs = new HashSet<Song>();
	private String name = "";

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
		this.songs = new HashSet<Song>(songs);
	}

	public void addSong(Song song) {
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

	/**
	 * tests if the name-string is "Favorites"
	 * 
	 * @param name
	 *            tested name
	 * @throws IllegalArgumentException
	 *             when the name is Favorites
	 */
	private void testName(String name) throws IllegalArgumentException {
		if (name.equals("Favorits")) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * sets the name of a plalist to "Favorites"
	 */
	public void setFavorite() {
		this.name = ("Favorites");
	}

	/**
	 * sets the name of the playlist but only if it is not "Favorites"
	 * 
	 * @param name
	 *            name for the playlist
	 */
	public void setName(String name) {
		try {
			testName(name);
			this.name = name;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Bitte geben sie Ihrer Playlist einen anderen Namen als \"Favorit\"");
		}

	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.hashCode() == obj.hashCode()) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getPlaylistId();
	}

	public void deleteSong(Song song) {
		songs.remove(song);
	}

	/**
	 * creates a new playlist out of the favorites-playlist of another user
	 * 
	 * @param u
	 *            user for whom is the new playlist
	 * @param p
	 *            plalist that gets copied
	 * @return new copied favorites-playlist
	 */
	public static Playlist copyFriendFavorites(User u, Playlist p) {
		Playlist copyF = new Playlist(u);
		copyF.setSongs(p.getSongs());
		copyF.setName("Favorites from " + p.getOwner().getUsername());
		return copyF;

	}

	@Override
	public String toString() {
		if (name == null || name.equals("")) {
			return "no name";
		}
		return getName();
	}
}
