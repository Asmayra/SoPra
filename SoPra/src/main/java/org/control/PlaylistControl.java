package org.control;


import java.util.LinkedList;

import org.model.Album;
import org.model.Playlist;
import org.model.User;
import org.testpackage.PlaylistTest;
import org.view.MainScreen;
import org.view.screens.Center.PlaylistExtendedScreen;
import org.view.screens.Center.PlaylistSingleScreen;

public class PlaylistControl {
	
	private User currentUser;
	private static PlaylistControl instance;
	private PlaylistExtendedScreen playlistScreen = PlaylistExtendedScreen.getInstance();
	
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
				
				
		Playlist current = playlistList.get(playlistnumber);
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
	
}
