package org.view.screens.Center;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.model.Genre;
import org.view.DiscoverElement;

/**
 * @author Tim Michels, Max Küper 
 */
public class DiscoverExtendedScreen extends JPanel{
	
	private LinkedList<DiscoverElement> discoverArtists;
	private LinkedList<DiscoverElement> discoverAlbums;
	private LinkedList<DiscoverElement> discoverSongs;
	private JScrollPane scrolArtist;
	private JScrollPane scrolAlbum;
	private JScrollPane scrolSong;
	private JTabbedPane tabPane = new JTabbedPane();
	private JPanel artist = new JPanel();
	private JPanel album = new JPanel();
	private JPanel song = new JPanel();
	
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
	 * Zerstört den Singleton
	 */
	public static void destroy()
	{
		instance = null;
	}
	
	/**
	 * Konstruktor
	 */
	public DiscoverExtendedScreen(){
		tabPane.setPreferredSize(new Dimension(500,500));
		artist.setLayout(new GridBagLayout());
		scrolArtist = new JScrollPane(artist);
		tabPane.addTab("Künstler", scrolArtist);
		
		album.setLayout(new GridBagLayout());
		scrolAlbum = new JScrollPane(album);
		tabPane.addTab("Album", scrolAlbum);
		
		song.setLayout(new GridBagLayout());
		scrolSong= new JScrollPane(song);
		tabPane.addTab("Song", scrolSong);
		
		this.add(tabPane);
	}
	
	/**
	 * Anordnen der Empfehlungen
	 */
	public void showRecommendations(){
		try{
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx=20;
		c.ipady=20;
		
		for(int i=0;i<discoverArtists.size();i++){
			
			c.gridx=(i%3);
			c.gridy=(i/3);
			artist.add(discoverArtists.get(i),c);
		}
		for(int i=0;i<discoverAlbums.size();i++){
			
			c.gridx=(i%3);
			c.gridy=(i/3);
			album.add(discoverAlbums.get(i),c);
		}
		for(int i=0;i<discoverSongs.size();i++){
			
			c.gridx=(i%3);
			c.gridy=(i/3);
			song.add(discoverSongs.get(i),c);
		}
		
		}catch(NullPointerException exc){System.err.println("error");}
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
