package org.testpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.model.Album;
import org.model.Playlist;
import org.model.Song;

/**
 * Testalben und -playlists
 * @author Tym
 *
 */

public class PlaylistTest {
	public LinkedList<Playlist> playlists = new LinkedList<Playlist>();
	public LinkedList<Album> albums = new LinkedList<Album>();
	
	public PlaylistTest(){
		for(int i=0;i<15;i++){
			Playlist neueListe= new Playlist();
			for(int j=0;j<10;j++){
				Song lied = new Song("Interpret"+i+","+j, "Titel", "url"+i);
				lied.setSongId(j);
				neueListe.addSong(lied);
			}
			neueListe.setPlaylistId(i);
			neueListe.setName("Playlist "+i);
			playlists.add(neueListe);
		}
		
		for(int i=0;i<10;i++){
			Album neueListe= new Album();
			for(int j=0;j<8;j++){
				Song lied = new Song("Interpret"+i+","+j, "Titel", "url"+i);
				neueListe.addSong(lied);
			}
			neueListe.setPlaylistId(i);
			neueListe.setName("Album "+i);
			albums.add(neueListe);
		}
	}

}
