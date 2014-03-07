package org.control;


import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import org.model.Album;
import org.model.Playlist;
import org.model.Song;
import org.model.User;
import org.testpackage.PlaylistTest;
import org.view.MainScreen;
import org.view.screens.Center.PlaylistExtendedScreen;
import org.view.screens.Center.PlaylistSingleScreen;

public class PlaylistControl {
	
	private User currentUser;
	private static PlaylistControl instance;
	private PlaylistExtendedScreen playlistScreen = PlaylistExtendedScreen.getInstance();
	private static Playlist current;
	private static Iterator<Song> playlistIterator;
	
	public static PlaylistControl getInstance()
	{
		if(instance == null)
			instance = new PlaylistControl();
		
		return instance;
	}

	
	public PlaylistControl() {
		currentUser = LoginControl.getInstance().getCurrentUser();
	}


	public void showPlaylist(int playlistnumber) {
		//ArrayList<Playlist> playlistList =  (ArrayList<Playlist>) currentUser.getPlaylists();
			
		//TEST:
		PlaylistTest TEST = new PlaylistTest();
		LinkedList<Playlist> playlistList =  (LinkedList<Playlist>) TEST.playlists;
		//Current Playlist und ihr iterator werden gesetzt		
		current = playlistList.get(playlistnumber);
		playlistIterator = current.getSongs().iterator();
		
		
		
		int tabindex = playlistScreen.getIndexOfTab(current.getPlaylistId());
		if(tabindex==-1){
			playlistScreen.addPlaylistTab(current.getName(), new PlaylistSingleScreen(current));
		}
		playlistScreen.setTabByIndex(tabindex);
		MainScreen.getInstance().showPlaylistExtendedScreen(playlistScreen);
	}


	public void showAlbum(int albumnumber) {
		//ArrayList<Album> albumList =  (ArrayList<Album>) currentUser.getAlbums();
		
		//TEST:
		PlaylistTest TEST = new PlaylistTest();
		LinkedList<Album> albumList =  (LinkedList<Album>) TEST.albums;
						
						
		Playlist current = albumList.get(albumnumber);
		int tabindex = playlistScreen.getIndexOfTab(current.getPlaylistId());
		if(tabindex==-1){
			playlistScreen.addPlaylistTab(current.getName(), new PlaylistSingleScreen(current));
		}
		playlistScreen.setTabByIndex(tabindex);
		MainScreen.getInstance().showPlaylistExtendedScreen(playlistScreen);
	}
	
	public static File nextSong(){	
		File file = null;
		while(playlistIterator.hasNext()){
			file = new File(playlistIterator.next().getPath());
		}	
		return file;	
	}
	/**
	 * Setzt die Aktuelle playlist
	 * @param playlist die neue aktuelle
	 */
	public static void setCurrentPlaylist(Playlist playlist){
		current = playlist;
		playlistIterator = current.getSongs().iterator();
	}


	
}
