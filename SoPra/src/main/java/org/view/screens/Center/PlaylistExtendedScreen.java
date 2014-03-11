package org.view.screens.Center;

import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.control.listener.PlaylistTabsListener;


/**
 * Zeigt die aufgerufenen Playlists in Tabs an
 * @author Tim Michels, Max Küper
 *
 */
public class PlaylistExtendedScreen extends JPanel{
	
	private JTabbedPane tabs = new JTabbedPane();
	private LinkedList<Integer> playlistIDs = new LinkedList<Integer>();
	
	private static PlaylistExtendedScreen instance;
	
	/**
	 * Gibt die Instanz des Screens zurück
	 * @return
	 */
	public static PlaylistExtendedScreen getInstance()
	{
		if(instance == null)
			instance = new PlaylistExtendedScreen();
		
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
	public PlaylistExtendedScreen(){
		tabs.addMouseListener(new PlaylistTabsListener());
		this.add(tabs);
	}
	
	/**
	 * Fügt Tab zu Ansicht hinzu
	 * @param playlistname Name des Tabs
	 * @param playlist Playlist, die im Tab angezeigt wird
	 */
	public void addPlaylistTab(String playlistname, PlaylistSingleScreen playlist){
		tabs.addTab(playlistname, playlist);
		playlistIDs.addLast(playlist.getPlaylistID());
	}
	
	/**
	 * Ändert einen konkreten Tab
	 * @param tabIndex Index des Tabs
	 * @param playlist neue Playlist
	 */
	public void setTabScreen(int tabIndex,PlaylistSingleScreen playlist){
		tabs.setComponentAt(tabIndex, playlist);
	}
	
	/**
	 * Entfern ein Tab
	 * @param tabindex
	 */
	public void removePlaylistTab(int tabindex){
		if(tabindex>=0 && tabindex<tabs.getTabCount()){
			tabs.remove(tabindex);
			playlistIDs.remove(tabindex);
			}
	}
	
	/**
	 * 
	 * @param playlistID
	 * @return Index des Tabs der Playlist mit der übergebenen ID. -1 falls inexistent.
	 */
	public int getIndexOfTab(int playlistID){
		return playlistIDs.indexOf(playlistID);
	}
	
	/**
	 * Setzt Tabnamen neu
	 * @param tabIndex Index des Tabs
	 * @param tabName neuer Tabname
	 */
	public void setTabName(int tabIndex, String tabName){
		if(tabIndex!=-1){
			tabs.setTitleAt(tabIndex, tabName);
			tabs.updateUI();
		}
	}
	
	public void setTabByIndex(int tabIndex){
		if(tabIndex!=-1){
			tabs.setSelectedIndex(tabIndex);}
		else{
			tabs.setSelectedIndex(tabs.getTabCount()-1);
		}
	}
	
}
