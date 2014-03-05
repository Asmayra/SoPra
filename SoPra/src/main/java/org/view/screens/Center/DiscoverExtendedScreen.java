package org.view.screens.Center;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.model.Genre;
import org.view.screens.EastBar.DiscoverElement;

/**
 * @author Tim Michels, Max Küper 
 */
public class DiscoverExtendedScreen extends JPanel{
	
	private LinkedList<DiscoverElement> discoverArtists;
	private LinkedList<DiscoverElement> discoverAlbums;
	private LinkedList<DiscoverElement> discoverSongs;
	private JScrollPane scrolPan;
	private JPanel help = new JPanel();
	
	private static DiscoverExtendedScreen instance = null;
	
	/**
	 * 
	 * @return Instanz des DiscoverExtendedScreen
	 */
	public static DiscoverExtendedScreen getInstance()
	{
		if(instance == null){
			instance = new DiscoverExtendedScreen();
		}
		return instance;
	}
	
	/**
	 * Konstruktor
	 */
	public DiscoverExtendedScreen(){
		this.setPreferredSize(new Dimension(500,500));
		help.setLayout(new GridBagLayout());
		showRecommendations();
		scrolPan = new JScrollPane(help);
		this.add(scrolPan);
	}
	
	/**
	 * Anordnen der Empfehlungen
	 */
	private void showRecommendations(){
		try{
		LinkedList<DiscoverElement> all = (LinkedList<DiscoverElement>) discoverArtists.clone();
		all.addAll(discoverAlbums);
		all.addAll(discoverSongs);
		System.out.println(all.size());
		
		for(int i=0;i<all.size();i++){
			GridBagConstraints c = new GridBagConstraints();
			c.gridx=(i%3);
			c.gridy=(i/3);
			help.add(all.get(i),c);
		}
		
		scrolPan.updateUI();
		}catch(NullPointerException exc){}
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
