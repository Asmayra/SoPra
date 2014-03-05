package org.view.screens.Center;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.model.Genre;
import org.view.screens.EastBar.DiscoverElement;

public class DiscoverExtendedScreen extends JPanel{
	
	private LinkedList<DiscoverElement> discoverArtists;
	private LinkedList<DiscoverElement> discoverAlbums;
	private LinkedList<DiscoverElement> discoverSongs;
	
	private static DiscoverExtendedScreen instance = null;
	
	public static DiscoverExtendedScreen getInstance()
	{
		if(instance == null){
			instance = new DiscoverExtendedScreen();
		}
		return instance;
	}
	
	
	public DiscoverExtendedScreen(){
		this.setPreferredSize(new Dimension(500,500));
		this.setLayout(new GridBagLayout());
		showRecommendations();
	}
	
	private void showRecommendations(){
		int numberofartists = discoverArtists.size();
		int numberofalbums = discoverAlbums.size();
		int numberofsongs = discoverSongs.size();
		
		LinkedList<DiscoverElement> all = (LinkedList<DiscoverElement>) discoverArtists.clone();
		all.addAll(discoverAlbums);
		all.addAll(discoverSongs);
		
		for(int i=0;i<all.size();i++){
			GridBagConstraints c = new GridBagConstraints();
			c.gridx=(i%3);
			c.gridy=(i/3);
			this.add(all.get(i),c);
		}
		
		this.updateUI();
	}
	

	public LinkedList<DiscoverElement> getDiscoverArtists() {
		return discoverArtists;
	}

	public void setDiscoverArtists(LinkedList<DiscoverElement> discoverArtists) {
		this.discoverArtists = discoverArtists;
	}

	public LinkedList<DiscoverElement> getDiscoverAlbums() {
		return discoverAlbums;
	}

	public void setDiscoverAlbums(LinkedList<DiscoverElement> discoverAlbums) {
		this.discoverAlbums = discoverAlbums;
	}

	public LinkedList<DiscoverElement> getDiscoverSongs() {
		return discoverSongs;
	}

	public void setDiscoverSongs(LinkedList<DiscoverElement> discoverSongs) {
		this.discoverSongs = discoverSongs;
	}
	
	
	
	
}
