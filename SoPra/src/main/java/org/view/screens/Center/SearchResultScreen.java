package org.view.screens.Center;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.view.DiscoverElement;

/**
 * @author Tim Michels
 */
public class SearchResultScreen extends JPanel{
	
	private LinkedList<DiscoverElement> searchArtists;
	private LinkedList<DiscoverElement> searchLabels;
	private LinkedList<DiscoverElement> searchSongs;
	private JScrollPane scrolResults;
	private JPanel resultPane = new JPanel();
	
	private static SearchResultScreen instance = null;
	
	/**
	 * 
	 * @return Instanz des SearchResultScreen
	 */
	public static SearchResultScreen getInstance()
	{
		if(instance == null){
			instance = new SearchResultScreen();
		}
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
	public SearchResultScreen(){
		resultPane.setLayout(new GridBagLayout());
		scrolResults= new JScrollPane(resultPane);
		this.add(scrolResults);
		this.setPreferredSize(new Dimension(500,500));
	}
	
	/**
	 * Anordnen der Suchresultate
	 */
	public void showRecommendations(){
		resultPane.removeAll();
		int aSize = searchSongs.size();
		int bSize= searchLabels.size();
		int cSize=searchArtists.size();
		try{
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx=20;
		c.ipady=20;
		
		for(int i=0;i<aSize;i++){
			
			c.gridx=(i%3);
			c.gridy=(i/3);
			resultPane.add(searchSongs.get(i),c);
		}
		for(int i=aSize;i<aSize+bSize;i++){
			
			c.gridx=(i%3);
			c.gridy=(i/3);
			resultPane.add(searchLabels.get(i-aSize),c);
		}
		for(int i=aSize+bSize;i<aSize+bSize+cSize;i++){
			
			c.gridx=(i%3);
			c.gridy=(i/3);
			resultPane.add(searchArtists.get(i-aSize-bSize),c);
		}
		
		}catch(NullPointerException exc){System.err.println("error");}
	}
	
	public LinkedList<DiscoverElement> getDiscoverArtists() {
		return searchArtists;
	}

	public void setDiscoverArtists(LinkedList<DiscoverElement> searchArtists) {
		this.searchArtists = searchArtists;
	}

	public LinkedList<DiscoverElement> getDiscoverLabels() {
		return searchLabels;
	}

	public void setDiscoverLabels(LinkedList<DiscoverElement> searchAlbums) {
		this.searchLabels = searchAlbums;
	}

	public LinkedList<DiscoverElement> getDiscoverSongs() {
		return searchSongs;
	}

	public void setDiscoverSongs(LinkedList<DiscoverElement> searchSongs) {
		this.searchSongs = searchSongs;
	}
	
}
