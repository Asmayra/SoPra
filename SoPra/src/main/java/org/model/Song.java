package org.model;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;
/**
 * Song Entity
 * @author Philipp
 *
 */
@Entity
@Table(name = "SONG_TABLE")
public class Song implements Comparable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int songId;
	private String interpret;
	private String title;
	private String location;
	@ManyToMany
	private Playlist playlists;
	@ManyToOne
	private Album album;
	private double playtime;
	private double vrgRating;
	private int ratingCount;
	
	public Song(){
		
	}
	
	
	public Song(String interpret, String title, String location, Album album) {
		this.interpret = interpret;
		this.title = title;
		this.location = location;
		vrgRating = 0;
		ratingCount = 0;
		this.album = album;
	}
	
	public Song(String interpret, String title){
		this.interpret = interpret;
		this.title = title;
		location = null;
		vrgRating = 0;
		ratingCount = 0;
		album = null;
		playtime = 0;
	}

	public Song(String interpret, String title, String location) {
		this.interpret = interpret;
		this.title = title;
		this.location = location;
		vrgRating = 0;
		ratingCount = 0;
		album = null;
	}
	
	public Playlist getPlaylists() {
		return playlists;
	}


	public void setPlaylists(Playlist playlists) {
		this.playlists = playlists;
	}

	public String getInterpret() {
		return interpret;
	}

	public String getTitle() {
		return title;
	}

	public double getPlaytime() {
		return playtime;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album a) {
		album = a;
	}
	
	//LÖSCHEN: NUR FÜR TESTZWECKE
	public void setSongId(int id){
		this.songId=id;
	}
	
	public int getSongId(){
		return songId;
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

	public void setPlaytime(){
		AudioFileFormat fileFormat;
		try {
			fileFormat = AudioSystem.getAudioFileFormat(new File("song.mp3"));
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
		
		return (this.songId-((Song)song).getSongId());
	}
	
	
	public String getPath(){
		return location;
	}
	
	public void setPath(String path)
	{
		location = path;
	}

}
