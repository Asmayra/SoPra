package org.view.screens.Center;

import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.control.listener.PlaylistTabsListener;


public class PlaylistExtendedScreen extends JPanel{
	
	private JTabbedPane tabs = new JTabbedPane();
	private LinkedList<Integer> playlistIDs = new LinkedList<Integer>();
	
	private static PlaylistExtendedScreen instance;
	
	public static PlaylistExtendedScreen getInstance()
	{
		if(instance == null)
			instance = new PlaylistExtendedScreen();
		
		return instance;
	}
	
	public PlaylistExtendedScreen(){
		tabs.addMouseListener(new PlaylistTabsListener());
		this.add(tabs);
	}
	
	public void addPlaylistTab(String playlistname, PlaylistSingleScreen playlist){
		tabs.addTab(playlistname, playlist);
		playlistIDs.addLast(playlist.getPlaylistID());
	}
	
	public void setTabScreen(int tabIndex,PlaylistSingleScreen playlist){
		tabs.setComponentAt(tabIndex, playlist);
	}
	
	public void removePlaylistTab(int tabindex){
		if(tabindex>=0 && tabindex<tabs.getTabCount()){
			tabs.remove(tabindex);
			playlistIDs.remove(tabindex);
			}
	}
	public int getIndexOfTab(int playlistID){
		return playlistIDs.indexOf(playlistID);
	}
	
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
