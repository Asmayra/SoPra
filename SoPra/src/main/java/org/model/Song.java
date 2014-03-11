package org.model;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.persistence.CascadeType;

import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 * Song Entity
 * 
 * @author Philipp, Sebastian Roth
 * 
 */
@Entity
@Table(name = "SONG_TABLE")
public class Song implements Comparable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int songId;
	private String interpret = "";
	private String title = "";
	private String location = "";

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Playlist> playlists = new HashSet<Playlist>();
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Genre> genre = new HashSet<Genre>();

	@ManyToOne
	private Album album;
	private int playtime;

	private boolean banned = false;

	public Song() {

	}

	public Song(String interpret, String title, String location, Album album) {
		this.interpret = interpret;
		this.title = title;
		this.location = location;
		this.album = album;
	}

	public Song(String interpret, String title) {
		this.interpret = interpret;
		this.title = title;
		location = "";
		album = null;
		playtime = 0;
	}

	public Song(String interpret, String title, String location) {
		this.interpret = interpret;
		this.title = title;
		this.location = location;
		album = null;
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

	// LÖSCHEN: NUR FÜR TESTZWECKE
	public void setSongId(int id) {
		this.songId = id;
	}

	public int getSongId() {
		return songId;
	}

	public void setPlaytime() {
		AudioFileFormat fileFormat;
		try {
			fileFormat = AudioSystem.getAudioFileFormat(new File(location));
			Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
			String key = "duration";
			Long microseconds = (Long) properties.get(key);
			int mili = (int) (microseconds / 1000);
			int sec = (mili / 1000);
			playtime = sec;
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int compareTo(Object song) {

		return (this.songId - ((Song) song).getSongId());
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
		return this.getSongId();
	}

	public String getPath() {
		return location;
	}

	public void setPath(String path) {
		location = path;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Genre> getGenre() {
		return genre;
	}

	public void addGenre(Genre genre) {
		this.genre.add(genre);
	}

	public void setBanned(boolean b) {
		banned = b;
	}

	public boolean getBanned() {
		return banned;
	}

}
