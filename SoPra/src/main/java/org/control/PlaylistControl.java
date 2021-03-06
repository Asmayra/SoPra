package org.control;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import javazoom.jlgui.basicplayer.BasicPlayerException;

import org.control.listener.PlayBTNListener;
import org.model.Album;
import org.model.Playlist;
import org.model.Song;
import org.model.User;
import org.view.MainScreen;
import org.view.screens.Center.PlaylistExtendedScreen;
import org.view.screens.Center.PlaylistSingleScreen;
import org.view.screens.Southbar.MusicPlayer;
import org.view.screens.WestBar.SongTicker;
/**
 * 
 * @author Philipp, Tim, Max
 *
 */
public class PlaylistControl {
	
	private User currentUser = LoginControl.getInstance().getCurrentUser();
	private static PlaylistControl instance;
	private PlaylistExtendedScreen playlistScreen = PlaylistExtendedScreen.getInstance();
	private static Playlist current = LoginControl.getInstance().getCurrentUser().getFavorites();
	private static ListIterator<Song> playlistIterator = current.getSongs().listIterator();
	private String[] playlistNames;
	private DatabaseControl control = DatabaseControl.getInstance();	
	
	/**
	 * 
	 * @return Instanz der Control
	 */
	public static PlaylistControl getInstance()
	{
		if(instance == null)
			instance = new PlaylistControl();
		
		return instance;
	}
	
	/**
	 * Zerstört den Singleton
	 */
	public static void destroy()
	{
		instance = null;
	}

	/**
	 * Konstruktor
	 */
	public PlaylistControl() {
		updatePlaylistNames();
	}

	/**
	 * Setzt MainscreenCenter auf die Playlistansicht mit der übergebenen Playlist(ID)
	 * @param playlistID
	 */
	public void showPlaylist(int playlistID) {
		int tabindex = playlistScreen.getIndexOfTab(playlistID);
		if(tabindex==-1){
			Playlist currentPlst = (Playlist) control.load( Playlist.class, playlistID);
			playlistIterator = (ListIterator<Song>) currentPlst.getSongs().listIterator();
			playlistScreen.addPlaylistTab(currentPlst.getName(), new PlaylistSingleScreen(currentPlst));
		}
		playlistScreen.setTabByIndex(tabindex);
		MainScreen.getInstance().showPlaylistExtendedScreen(playlistScreen);
	}


	/**
	 * Wie showPlaylist für Alben
	 * @param albumID
	 */
	public void showAlbum(int albumID) {
		int tabindex = playlistScreen.getIndexOfTab(albumID);
		if(tabindex==-1){
			Album currentAlbm = (Album) control.load( Album.class, albumID);
			playlistIterator = (ListIterator<Song>) currentAlbm.getSongs().listIterator();
			playlistScreen.addPlaylistTab(currentAlbm.getName(), new PlaylistSingleScreen(currentAlbm));
		}
		playlistScreen.setTabByIndex(tabindex);
		MainScreen.getInstance().showPlaylistExtendedScreen(playlistScreen);
	}
	
	/**
	 * Zeigt die Favoriten im MainScreenCenter an
	 */
	public void showFavorites(){
		currentUser = LoginControl.getInstance().getCurrentUser();
		Playlist favorites = currentUser.getFavorites();
		System.out.println(favorites.getPlaylistId());
		int tabindex = playlistScreen.getIndexOfTab(favorites.getPlaylistId());
		if(tabindex==-1){
			Playlist favos = (Playlist) control.load(Playlist.class, favorites.getPlaylistId());
			LinkedList<Song> favoList = favos.getSongs();
			playlistIterator = (ListIterator<Song>) favoList.listIterator();
			playlistScreen.addPlaylistTab("Favoriten", new PlaylistSingleScreen(favos));
		}
		playlistScreen.setTabByIndex(tabindex);
		MainScreen.getInstance().showPlaylistExtendedScreen(playlistScreen);
	}
	/**
	 * Gibt nächstes Lied aus
	 * @return Song als File
	 */
	public static File nextSong(){	
		File file = null;
		if(playlistIterator.hasNext()){
			Song song = playlistIterator.next();
			file = new File(song.getPath());
			SongTicker.setTickertext(song.getInterpret()+" - "+ song.getTitle()+"       ");
			SongTicker.getInstance().start();
		}	
		return file;	
	}
	/**
	 * gibt vorheriges Leid aus
	 * @return Song als File
	 */
	public static File prevSong(){	
		File file = null;	
		if(playlistIterator.hasPrevious()){
			file = new File(playlistIterator.previous().getPath());
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
	/**
	 * Sucht ein bestimmtes Lied aus der playlist und setzt es als aktuell.<br>
	 * Es passt den Iterator an!
	 * @param singId
	 */
	public static void searchForSong(int songId){
		while(playlistIterator.hasPrevious()){
			playlistIterator.previous();	//Laufe zurück zum Anfang
		}
		boolean found = false;
		Song result;
		while(playlistIterator.hasNext()&&!found){
			result = playlistIterator.next();
			if(result.getSongId() == songId){
				found=true;
				System.out.println("SongFound");
				File file = new File(result.getPath());
				MusicPlayer.setCurrentSong(file);
				SongTicker.setTickertext(result.getInterpret()+" - "+ result.getTitle()+"       ");
				SongTicker.getInstance().start();
				try {
					MusicPlayer.getPlayer().open(file);
					MusicPlayer.getPlayer().play();
				} catch (BasicPlayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	/**
	 * Setzt die Playlistnamen neu
	 */
	public void updatePlaylistNames(){
		ArrayList<Playlist> playlists = (ArrayList<Playlist>) LoginControl.getInstance().getCurrentUser().getPlaylists();
		playlistNames=new String[playlists.size()];
		for(int i=0;i<playlists.size();i++){
			playlistNames[i]=playlists.get(i).getName();
		}
	}

	/**
	 * 
	 * @return Namen aller Playlists
	 */
	public String[] getPlaylistNames() {
		return playlistNames;
	}

}
