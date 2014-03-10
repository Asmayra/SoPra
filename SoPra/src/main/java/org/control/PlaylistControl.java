package org.control;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import org.model.Album;
import org.model.Playlist;
import org.model.Song;
import org.model.User;
import org.testpackage.PlaylistTest;
import org.view.MainScreen;
import org.view.screens.Center.PlaylistExtendedScreen;
import org.view.screens.Center.PlaylistSingleScreen;
/**
 * 
 * @author Philipp, Tim, Max
 *
 */
public class PlaylistControl {
	
	private User currentUser;
	private static PlaylistControl instance;
	private PlaylistExtendedScreen playlistScreen = PlaylistExtendedScreen.getInstance();
	private static Playlist current;
	private static ListIterator<Song> playlistIterator;
	private String[] playlistNames;
	private DatabaseControl control = DatabaseControl.getInstance();	
	
	public static PlaylistControl getInstance()
	{
		if(instance == null)
			instance = new PlaylistControl();
		
		return instance;
	}

	
	public PlaylistControl() {
		currentUser = LoginControl.getInstance().getCurrentUser();
		updatePlaylistNames();
	}


	public void showPlaylist(int playlistID) {
		int tabindex = playlistScreen.getIndexOfTab(playlistID);
		if(tabindex==-1){
			Playlist current = (Playlist) control.load( Playlist.class, playlistID);
			playlistIterator = (ListIterator<Song>) current.getSongs().listIterator();
			playlistScreen.addPlaylistTab(current.getName(), new PlaylistSingleScreen(current));
		}
		playlistScreen.setTabByIndex(tabindex);
		MainScreen.getInstance().showPlaylistExtendedScreen(playlistScreen);
	}


	public void showAlbum(int albumID) {
		int tabindex = playlistScreen.getIndexOfTab(albumID);
		if(tabindex==-1){
			Album current = (Album) control.load( Album.class, albumID);
			playlistIterator = (ListIterator<Song>) current.getSongs().listIterator();
			playlistScreen.addPlaylistTab(current.getName(), new PlaylistSingleScreen(current));
		}
		playlistScreen.setTabByIndex(tabindex);
		MainScreen.getInstance().showPlaylistExtendedScreen(playlistScreen);
	}
	
	public void showFavorites(){
		Playlist current = currentUser.getFavorites();
		int tabindex = playlistScreen.getIndexOfTab(current.getPlaylistId());
		if(tabindex==-1){
			playlistScreen.addPlaylistTab("Favoriten", new PlaylistSingleScreen(current));
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
	public static File prevSong(){	
		File file = null;
		
		while(playlistIterator.hasPrevious()){
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
		playlistIterator = (ListIterator<Song>) current.getSongs().listIterator();
	}

	public void savePlaylists(){
		//TODO Schreibe aktuelle Userplaylists in die Datenbank
	}
	
	public void removeSong(Playlist playlist,int songnr){
		playlist.deleteSong(playlist.getSongs().get(songnr));
		savePlaylists();
	}
	
	public void updatePlaylistNames(){
		ArrayList<Playlist> playlists = (ArrayList<Playlist>) LoginControl.getInstance().getCurrentUser().getPlaylists();
		playlistNames=new String[playlists.size()];
		for(int i=0;i<playlists.size();i++){
			playlistNames[i]=playlists.get(i).getName();
		}
	}


	public String[] getPlaylistNames() {
		return playlistNames;
	}
	public void deletePlaylist(int iD){
		//TODO control.);;//die favoritenliste darf nicht gelöscht werden
	}
	public void deleteAlbum(int iD){
		//TODO control.);;//die favoritenliste darf nicht gelöscht werden
	}
}
