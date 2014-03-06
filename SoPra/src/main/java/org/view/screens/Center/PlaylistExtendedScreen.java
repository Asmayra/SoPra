package org.view.screens.Center;

import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class PlaylistExtendedScreen extends JPanel{
	
	private JTabbedPane tabs = new JTabbedPane();
	private LinkedList<String> playlistIDs = new LinkedList<String>();
	
	private static PlaylistExtendedScreen instance;
	
	public static PlaylistExtendedScreen getInstance()
	{
		if(instance == null)
			instance = new PlaylistExtendedScreen();
		
		return instance;
	}
	
	public PlaylistExtendedScreen(){
		this.add(tabs);
	}
	
	public void addPlaylistTab(String playlistname, PlaylistSingleScreen playlist){
		tabs.addTab(playlistname, playlist);
		playlistIDs.addLast(playlistname);
	}
	
	public void removePlaylistTab(int tabindex){
		tabs.remove(tabindex);
		playlistIDs.remove(tabindex);
	}
	public int getIndexOfTab(int playlistID){
		return playlistIDs.indexOf(playlistID);
	}
	
	public void setTabByIndex(int tabIndex){
		if(tabIndex!=-1){
			tabs.setSelectedIndex(tabIndex);}
		else{
			tabs.setSelectedIndex(tabs.getTabCount());
		}
	}
	
}
