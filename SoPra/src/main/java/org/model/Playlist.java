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
	private Set<Song> songs;
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

	public Collection<Song> getSongs() {
		Collection<Song> coll = new ArrayList<Song>(this.songs);
		return coll;
	}

	public void setSongs(List<Song> songs) {
		this.songs = new TreeSet<Song>(songs);
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
		for (int i = 0; i < owner.getPlaylists().size(); i++) {
			if (name.equals(owner.getPlaylists().get(i).getName())) {
				throw new IllegalArgumentException();
			}
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
							"Jede ihrer Playlists braucht einen eigentständigen Namen.\n Bitte wählen sie einen neuen, unbenutzten Namen.");
		}

	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
