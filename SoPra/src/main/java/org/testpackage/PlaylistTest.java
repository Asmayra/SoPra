package org.testpackage;

import java.util.Collection;

import org.model.Album;
import org.model.Playlist;
import org.model.Song;

public class PlaylistTest {
	public Collection<Playlist> playlists;
	public Collection<Album> albums;
	
	public PlaylistTest(){
		for(int i=0;i<15;i++){
			Playlist neueListe= new Playlist();
			for(int j=0;j<10;j++){
				Song lied = new Song("Interpret"+i+","+j, "Titel", "url");
				neueListe.addSong(lied);
			}
			playlists.add(neueListe);
		}
		
		for(int i=0;i<10;i++){
			Album neueListe= new Album();
			for(int j=0;j<8;j++){
				Song lied = new Song("Interpret"+i+","+j, "Titel", "url");
				neueListe.addSong(lied);
			}
			albums.add(neueListe);
		}
	}

}
